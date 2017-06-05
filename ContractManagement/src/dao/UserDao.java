package dao;

import model.User;
import utils.AppException;


/**
 * User Data Access Layer Interface
 */
public interface UserDao {
	
	/** 
	 * Verify whether there are  users that has the same name exists
	 * 
	 * @param name  User name
	 * @return Return true if there are users have same name,otherwise return false 
	 * @throws AppException
	 */
	public boolean isExist(String name) throws AppException;
	
	/**
	 * Save user information
	 * 
	 * @param user  user object
	 * @return Return true if saved successfully,otherwise return false
	 * @throws AppException
	 */
	public boolean add(User user) throws AppException;
	
}