package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user's request,output login page
 */
public class ToLoginServlet extends HttpServlet {

	/**
	 *  Process the POST requests,output login page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * ¥¶¿ÌGET«Î«Û, output login page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}


