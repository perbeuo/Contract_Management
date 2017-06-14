package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for accessing contract operator page
 */
public class ToOperatorServlet extends HttpServlet {

	/**
	 * Jump to contract operator page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");
		
		// 声明会话
		HttpSession session = null;
		// 获得会话 object
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// If the user is not loggin, then jump to login page
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// Forward to the contract operator page
			request.getRequestDispatcher("/frame2.jsp").forward(request, response);
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
