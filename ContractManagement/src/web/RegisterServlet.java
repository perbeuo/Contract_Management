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
		
		//Declare the identifies of handling registration
		boolean flag = false;
		//Initialize the prompt message 
		String message = "";
		try {
			//Instantiate the object of entity class User  
			User user = new User();
			// Initialize the user business logic class
			UserService userService = new UserService();
			// Encapsulate the user information to the user object
			user.setName(name);
			user.setPassword(password);
			// Call business logic layer for user registration 
			flag = userService.register(user);
			if (flag) {
				// After registration Successful, redirect to the login page
				message = "Registration Succeed";
				request.setAttribute("message", message);
				//response.sendRedirect("toLogin");
				request.getRequestDispatcher("/toLogin").forward(request,
						response);
			} else {
				// Set prompt message
				message = "Registration failed";
				request.setAttribute("message", message); // Save prompt message into request 
				// Forward to the registration page
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// Redirect to the exception page
			response.sendRedirect("toError");
		}
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
