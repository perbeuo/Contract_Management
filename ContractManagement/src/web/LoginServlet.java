package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import utils.AppException;
/**
 * Servlet that process user login 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * Process login request with POST mode
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set request's character encoding
		request.setCharacterEncoding("UTF-8");
		// Get user name and password
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// Initialize prompt message
		String message = "";

		/*
		 * Process login request, verify the following: user name and password can not be empty.
		 * 
		 * If do not meet the above requirements, so login fails, give prompt message.
		 */
		if (name == "" || password == "") {
			System.out.println("---Entered incorrectly!---");
			System.out.println("User name and password can not be empty!");
			message = "User name and password can not be empty!";
			// Save message into request
			request.setAttribute("message", message);
			// Forward to the login page
			request.getRequestDispatcher("toLogin").forward(request, response);
		} else {
			/*
			 * Process login, query user id based on user name and password,login here is using hard-coded validation 
			 * Returns the value of user id  after a successful login.
			 */
			//Initialize userId, it used to receive the user id that processing login
			int userId = -1;
			try { 
				// Initialize  user business logic class
				UserService userService = new UserService();
				// Call business logic layer to query userId
				userId = userService.login(name, password);

				if (userId > 0) { // Login successfully
					// Declare  session
					HttpSession session = null;
					// Get session by using request 
					session = request.getSession();
					// Save userId and user name into session
					session.setAttribute("userId", userId);
					session.setAttribute("userName", name);
					// Redirect to the new user page
					response.sendRedirect("toNewUser");
				} else { // If login failed, jump to login page and display prompt message.
					message = "Incorrect user name or password!";
					// Save message into request
					request.setAttribute("message", message);
					//Transfer login user name to page for display
					request.setAttribute("userName", name);

					// Forward to login page
					request.getRequestDispatcher("toLogin").forward(request,
							response);
				}
			}catch (AppException e) {
				e.printStackTrace();
				// Redirect to the exception page
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 * Process GET requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Call doPost() to process request
		this.doPost(request, response);
	}

}
