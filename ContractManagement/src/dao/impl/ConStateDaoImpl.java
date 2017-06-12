package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 * @param  conState Contract state object
	 * @return boolean Return true if successful , otherwise false
	 * @throws AppException
	 */
	public boolean add(ConState conState) throws AppException{	
		boolean flag = false;// Operation flag
		//Declare Connection object,PreparedStatement object
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,save contract operation state, "?" is a placeholder
			String sql = "insert into t_contract_state(con_id,type) values(?,?)";
				
			psmt = conn.prepareStatement(sql);// Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, conState.getConId());
			psmt.setInt(2, conState.getType());
		
			int result = psmt.executeUpdate();// Execute update 
			
			if(result > 0){// If affected lines greater than 0, so operation success
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"dao.impl.ConStateDaoImpl.add");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}
	
	/**
	 * Query contract id set that meet the conditions according to contract type
	 * 
	 * @param type Operation type
	 * @return Contract id set
	 * @throws AppException
	 */
	public List<Integer> getConIdsByType(int type) throws AppException {
		// Initialize  conIds
		List<Integer> conIds = new ArrayList<Integer>();
		
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Declare operation statement,query contract ids that meet the conditions , "?" is a placeholder
			String sql = "select con_id from t_contract_state where type=? and del=0";
				
			psmt = conn.prepareStatement(sql);//Pre-compiled sql
			// Set values for the placeholder 
			psmt.setInt(1, type);
			
			// Execute query operation 
			rs = psmt.executeQuery();
			
			//Get information in result set by loop,and save it to conIds
			while (rs.next()) {
				conIds.add(rs.getInt("con_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
			"com.ruanko.dao.impl.ConStateDaoImpl.getConIdsByType");
		} finally {
			// Close database object operation, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conIds;
	}

	/**
	 * Query contract state information according to contract id and type
	 * 
	 * @param conId Contract id
	 * @param type Operation type
	 * @return Contract state object
	 * @throws AppException
	 */
	public ConState getConState(int conId, int type) throws AppException {
		// Declare conState
		ConState conState = null;

		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			//Define SQL statement: query contract state information according to the contract id and type
			String sql = "select id,con_id,type,time "
					+"from t_contract_state "
					+"where con_id = ? and type = ? and del = 0";

			//Pre-compiled sql, and set the parameter values
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, conId); //Set contract id
			psmt.setInt(2, type);
			
			// Query result set
			rs = psmt.executeQuery();

			//Get information in result set by loop,and encapsulated into conState object
			if(rs.next()) {
				conState = new ConState();
				conState.setId(rs.getInt("id"));
				conState.setConId(rs.getInt("con_id"));
				conState.setType(rs.getInt("type"));
				conState.setTime(rs.getDate("time"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(
					"dao.impl.ConStateDaoImpl.getByConId");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return conState;
	}

}
