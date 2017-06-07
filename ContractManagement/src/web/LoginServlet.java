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
		
		int userId = -1;
		try {
			// Initialize the user business logic class
			UserService userService = new UserService();
			// Call business logic layer for user login
			userId = userService.login(name, password);
			if (userId > 0) { // Login successfully  
				// Declare session
				HttpSession session = null;
				// Get session by using request
				session = request.getSession();
				// Save userId and user name into session
				session.setAttribute("userId", userId);
				session.setAttribute("userName", name);
				// Redirect to new user page
				response.sendRedirect("toNewUser");
			} else {// Login failed
				// Set prompt message
				message = "Incorrect user name or password!";
				request.setAttribute("message", message); // Save prompt message into request
				// Forward to login page
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// Redirect to exception page
			response.sendRedirect("toError");
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
