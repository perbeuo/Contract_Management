package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.RoleDao;
import model.Role;
import utils.AppException;
import utils.DBUtil;

/**
 * Role data access layer implementation class
 */
public class RoleDaoImpl implements RoleDao {

	/**
	 * Query role's information according to id
	 * 
	 * @param id 
	 * @return Role 
	 * @throws AppException
	 */
	public Role getById(int id) throws AppException {
		// Declare role object
		Role role = null;
		//Declare Connection object,PreparedStatement object and ResultSet object
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			// Create database connection
			conn = DBUtil.getConnection();
			// Declare operation statement,query role's information based on role id, "?" is a placeholder
			String sql = "select id,name,description,function_ids "
					+"from t_role "
					+"where id = ? and del = 0";
			// Pre-compiled sql
			psmt = conn.prepareStatement(sql);
			// Set values for the placeholder  '?'
			psmt.setInt(1, id);
			// Query result set
			rs = psmt.executeQuery();
			
			// Save user information by using Pole entity object when queried the results set 
			if (rs.next()) {
				role = new Role(); // Instantiate role object
				// Set value for role object
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				role.setFuncIds(rs.getString("function_ids"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("dao.impl.RoleDaoImpl.getById");
		} finally {
			// Close the database operation object, release resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return role;
	}

}
