package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Process user's request,output new user page
 */
public class ToNewUserServlet extends HttpServlet {

	/**
	 * Process the POST requests,output new user page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * Process the GET requests,output new user page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");// Set the types of output content 
		response.setCharacterEncoding("UTF-8");// Set the character encoding of output content
		PrintWriter out = response.getWriter();// Get the output object
		// Output the <!DOCTYPE> declaration
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		// Output the standard HTML structure
		out.println("<html>\r\n");
		out.println("<head>\r\n");
		// Set the character encoding for the HTML document
		out.println("<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />\r\n");
		out.println("<title>New user page</title>\r\n");
		out.println("</head>\r\n");
		out.println("<body>\r\n");
		out.println("<br />\r\n");
		
		// Declare session
		HttpSession session = null;
		// Get session by using request object
		session = request.getSession();
		/*
		 * User login verification,show welcome information if already login,and provide "logout" link
		 *  Otherwise redirect to login page
		 */
		if (session.getAttribute("userName") != null) {
			// If user is already login,output welcome information and user information
			out.println("Hello!");
		    out.print(session.getAttribute("userName"));
		    out.println("<br />\r\n");
		    out.println("You hava no contract operation privileges, please waiting for the administrator configure permissions for you\r\n");
		    out.println("<br />\r\n");
		    //  Output "logout" link
		    out.println("<br />\r\n");
		    out.println("<a href=\"logout\">Logout</>\r\n");
		} else {
			// User is not login, redirect to login page
			response.sendRedirect("toLogin");
		}
		out.println("</body>\r\n");
		out.println("</html>\r\n");
		// Clear and close the output stream
		out.flush();
		out.close();
	}
}


