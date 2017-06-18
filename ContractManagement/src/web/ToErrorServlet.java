package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 输出异常
 */
public class ToErrorServlet extends HttpServlet {

	/**
	 * 处理POST请求, 输出异常
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to Process request
		doGet(request, response); 
	}

	/**
	 * 处理GET请求, 输出异常
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
}


