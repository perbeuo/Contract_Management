package service;

import model.User;
import utils.AppException;
import dao.UserDao;
import dao.impl.UserDaoImpl;;

/**
 * User business logic class
 */
public class UserService {
	
	private UserDao userDao = null;// Define a userDao interface object

	/**
	 * No-arg constructor method is used to initialize userDao instance
	 */
	public UserService() {
		userDao = new UserDaoImpl();
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
	/**
	 * 
	 * test the function just added
	 */
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
}
