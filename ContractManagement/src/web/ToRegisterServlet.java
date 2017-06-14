package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user's request, output the target page
 */
public class ToRegisterServlet extends HttpServlet {

	/**
	 * Process the POST requests, output the target page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * 处理GET请求, output the target page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}
}


