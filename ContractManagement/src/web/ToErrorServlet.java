package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����쳣
 */
public class ToErrorServlet extends HttpServlet {

	/**
	 * ����POST����, ����쳣
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to Process request
		doGet(request, response); 
	}

	/**
	 * ����GET����, ����쳣
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
}


