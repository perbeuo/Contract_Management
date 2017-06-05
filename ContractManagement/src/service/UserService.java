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
	 * 
	 * test the function just added
	 */
/*	public static void main(String[] args) {
		//Instantiate the object of entity class User 
		User user = new User();
		// Initialize the user business logic class
		UserService userService = new UserService();
		boolean flag = false;
		user.setName("test");
		user.setPassword("testpass");
		try {
			flag = userService.register(user);
			System.out.println(flag);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
