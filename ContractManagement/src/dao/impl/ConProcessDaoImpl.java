package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ConProcessDao;
import model.ConProcess;
import utils.AppException;
import utils.DBUtil;

/**
 * Contract process data access layer implementation class
 */
public class ConProcessDaoImpl implements ConProcessDao{
	
	/**
	 * Determine whether the record of specified contract id is exist in the contract process table
	 * 
	 * @param conId Contract id
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean isExist(int conId) throws AppException{
		boolean flag = false;// Operation flag
		
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:specifies the amount of contract Id's records, "?" is a placeholder
			String sql = "select count(id) as n from t_contract_process where con_id = ? and del = 0";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conId);
			// Execute query operation
			rs = psmt.executeQuery();
			rs.next();
			int n =  rs.getInt("n"); // Get the number of records
			if (n > 0) {
				flag = true; // Designated contract ID exist
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.isExist");
		} finally {
			// Close database object operation, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Add contract operation process information
	 * 
	 * @param  conProcess Contract process object
	 * @return boolean Return true if exist,otherwise return false
	 * @throws AppException
	 */
	public boolean add(ConProcess conProcess)  throws AppException{	
		boolean flag = false;// Operation flag
		// Declare database connection object, pre-compiled object and result set object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement:save contact operation process information, "?" is a placeholder
			String sql = "insert into t_contract_process(con_id,user_id,type,state,content) values(?,?,?,?,?)";
				
			psmt = conn.prepareStatement(sql);// pre-compiled sql
			// Set values for the placeholder
			psmt.setInt(1, conProcess.getConId());
			psmt.setInt(2, conProcess.getUserId());
			psmt.setInt(3, conProcess.getType());
			psmt.setInt(4, conProcess.getState());
			psmt.setString(5, conProcess.getContent());
		
			int result = psmt.executeUpdate();// Execute update
			
			if(result > 0){// If the affected lines greater than 0, the operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConProcessDaoImpl.add");
		} finally {
			// Close database object operation, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
}
