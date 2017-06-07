package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Output exception user page
 */
public class ToErrorServlet extends HttpServlet {

	/**
	 * Process POST request, output exception user page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to Process request
		doGet(request, response); 
	}

	/**
	 * Process GET request, output exception user page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
}


