package dao;

import java.util.List;

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
	
	/**
	 * Query user id according to the user name and password
	 * @param name 
	 * @param password 
	 * @return User id
	 * @throws AppException 
	 */
	public int login(String name,String password) throws AppException;
	
	/**
	 * Query user's information according to id
	 * 
	 * @param id  User id
	 * @return User 
	 * @throws AppException
	 */
	public User getById(int id) throws AppException;
	
	/**
	 * Query user id set
	 * 
	 * @return User id set
	 * @throws AppException
	 */
	public List<Integer> getIds() throws AppException;
}
