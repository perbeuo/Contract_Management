package service;

import model.User;
import model.Role;
import utils.AppException;
import dao.*;
import dao.impl.*;

/**
 * User business logic class
 */
public class UserService {
	
	private UserDao userDao = null;// Define a userDao interface object
	private RoleDao roleDao = null;// Define a roleDao interface object
	private RightDao rightDao = null;// Define a userDao rightDao object

	/**
	 * No-arg constructor method is used to initialize userDao instance
	 */
	public UserService() {
		userDao = new UserDaoImpl();
		roleDao = new RoleDaoImpl();
		rightDao = new RightDaoImpl();
	}
	
	/**
	 * User registration
	 * @param user User object
	 * @return Return true if registration is successful, otherwise return false
	 * @throws AppException
	 */
	public boolean register(User user) throws AppException {
		boolean flag = false;//Define flag
		try {
			if (!userDao.isExist(user.getName())) {// Execute save operation when the user does not exist
				flag = userDao.add(user);// Return the operation result back to flag
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.register error");
		}
		return flag;
	}
	
	/**
	 * User login
	 * 
	 * @param name 
	 * @param password 
	 * @return Query the matched user number according to the condition , otherwise it returns 0
	 * @throws AppException
	 */
	public int login(String name, String password) throws AppException {
		int userId = -1; // Declare userId
		try {
			// Get userId
			userId = userDao.login(name, password); 
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.login");
		}
		//  Return userId
		return userId;
	}
  
	public static void main(String[] args) {
		//Instantiate the object of entity class User 
		User user = new User();
		// Initialize the user business logic class
		UserService userService = new UserService();
		int id = -1;
		try {
			id = userService.login("test", "testpass");
			System.out.println(id);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the role information that corresponding to the user
	 * 
	 * @param userId 
	 * @return Role object
	 * @throws AppException
	 */
	public Role getUserRole(int userId) throws AppException {	
		Role role = null;// Declare role
		int roleId = -1; // Initialize  roleId
		try {
			//  Get the roleId that corresponding to the user
			roleId = rightDao.getRoleIdByUserId(userId);
			if(roleId > 0){
				// Get role information
				role = roleDao.getById(roleId); 
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new AppException("service.UserService.getUserRole");
		}
		return role;
	}
}
