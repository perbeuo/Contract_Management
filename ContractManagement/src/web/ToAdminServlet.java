package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 进入管理员页面的Servlet 
 */
public class ToAdminServlet extends HttpServlet {

	/**
	 * 跳转到管理员页面
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");
		
		// 声明会话
		HttpSession session = null;
		// 获得会话 
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		// 如果用户没有登陆，则跳转到登录页面
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// Forwarded 到管理员页面
			request.getRequestDispatcher("/frame1.jsp").forward(request, response);
		}
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
