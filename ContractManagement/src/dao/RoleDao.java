package dao;

import model.Role;
import utils.AppException;

/**
 * Role Data Access Layer Interface
 */
public interface RoleDao {

	/**
	 * Query role's information according to id
	 * 
	 * @param id RoleId
	 * @return Role  
	 * @throws AppException
	 */
	public Role getById(int id) throws AppException;
	
}
