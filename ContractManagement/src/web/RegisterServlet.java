package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import utils.AppException;
/**
 * Handle the registration request
 */
public class RegisterServlet extends HttpServlet {

	/**
	 * Handle the registration request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Set the request's character encoding
		request.setCharacterEncoding("UTF-8");
		//Get the user's name, password and repeat password
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		//Declare the identifies of handling registration
		boolean flag = false;
		//Initialize the prompt message 
		String message = "";
		//Instantiate the object of entity class User 
		User user = new User();
		// Initialize the user business logic class
		UserService userService = new UserService();
				
		/*
		 * Handle registration request, verify the following:
		 * 1.user name, password, and the repeated password can not be empty.
		 * 2.Repeated password and password should keep consistent.
		 * 
		 * If fails to meet the above requirements, the registration failed.
		 */
		
		if(name == "" || password == "" || password2 == ""){
			System.out.println("Entered incorrectly! ---");
			System.out.println("User name, password, and the repeated password can not be empty!");
			message = "User name, password, and the repeat password can not be empty!";	
		} else if(!password2.equals(password)){
			System.out.println("Entered incorrectly!---");
			System.out.println("Repeated password and password should keep consistent!");
			message = "Repeat password and password should keep consistent!";
		} else {
			try {
				// Encapsulate the user information to object of user entity class object User 
				user.setName(name);
				user.setPassword(password);
				// Call business logic for user registration
				flag = userService.register(user);
				if (flag) {
					// If Registration is successful, set the prompt message
					message = "Registration Successful!";
					System.out.println("Registration Successful!");
				} else {
					// If Registration is failed, set the prompt message
					message = "Registration failed!";
					System.out.println("Registration failed!");
				}
			} catch (AppException e) {
				e.printStackTrace();
				// If the system exception, set the prompt message
				message = "System exception!";
			}
		}
		//Save message into request
		request.setAttribute("message", message);
		// Forward to the registration page
		request.getRequestDispatcher("/toRegister").forward(request,
				response);
	}

	/**
	 * Handle the registration request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doPost() to handle request
		doPost(request, response);
	}
	
	
}
