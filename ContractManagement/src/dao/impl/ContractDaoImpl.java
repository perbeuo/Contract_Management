package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ContractDao;
import model.Contract;
import utils.AppException;
import utils.DBUtil;

/**
 * Contract data access layer implementation class
 */
public class ContractDaoImpl implements ContractDao {

	/**
	 * Add contract information
	 * 
	 * @param contract 
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(Contract contract) throws AppException{
		boolean flag = false;// Operation flag 
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,save contract information, "?" is a placeholder
			String sql = "insert into t_contract" 
				+"(user_id,customer,num,name,beginTime,endTime,content) "
				+"values(?,?,?,?,?,?,?)";
				
			// Pre-compiled sql, and return primary key
            psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 

			// Set values for the placeholder 
			psmt.setInt(1, contract.getUserId());
			psmt.setString(2, contract.getCustomer());
			psmt.setString(3, contract.getNum());
			psmt.setString(4, contract.getName());
			// Turn java.util.Dat to java.sql.Date
			java.sql.Date beginTime = new java.sql.Date(contract.getBeginTime().getTime());
			java.sql.Date endTime = new java.sql.Date(contract.getEndTime().getTime());
			psmt.setDate(5, beginTime);
			psmt.setDate(6, endTime);
			psmt.setString(7, contract.getContent());
			
			psmt.executeUpdate();// Execute update 
			rs = psmt.getGeneratedKeys();  //Get primary key in  insert row,only one record in result set

			if (rs.next()) {
				contract.setId(rs.getInt(1));// Get primary key's value,and set it into contract object
				flag = true; // If affected lines greater than 0, so operation success
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ContractDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
}
