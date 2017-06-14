package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理用户注销的Servlet
 */
public class LogoutServlet extends HttpServlet{

	/**
	 * 处理注销请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 声明会话
		HttpSession session = null;
		// 获得会话 object
		session = request.getSession();
		// 从会话中删除信息
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		// 重定向到登录界面
		response.sendRedirect("toLogin");
	}

	/**
	 * 处理GET请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用doPost方法来处理请求
		this.doPost(request, response);
	}
	
}
