package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ConStateDao;
import model.ConState;
import utils.AppException;
import utils.DBUtil;

/**
 * Contract state data access layer implementation class
 */
public class ConStateDaoImpl implements ConStateDao {

	/**
	 * Add contract operation state information
	 *  
	 * @param  conState Contract status object
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException{	
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,save contract operation state, "?" is a placeholder
			String sql = "insert into t_contract_state(con_id,type) values(?,?)";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set value for the placeholder 
			psmt.setInt(1, conState.getConId());
			psmt.setInt(2, conState.getType());
		
			int result = psmt.executeUpdate();// Execute update 
			
			if(result > 0){// If affected lines greater than 0, so operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ContStateDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

}
