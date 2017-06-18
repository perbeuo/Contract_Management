package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户请求, 输出注册页面
 */
public class ToRegisterServlet extends HttpServlet {

	/**
	 * 处理POST请求,  输出注册页面
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * 处理GET请求,  输出注册页面
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}
}


