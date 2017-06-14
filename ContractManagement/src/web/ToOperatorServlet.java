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
		// �趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		
		// �����Ự
		HttpSession session = null;
		// ��ûỰ object
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
	 * ����GET����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����doPost��������������
		this.doPost(request, response);
	}
}
