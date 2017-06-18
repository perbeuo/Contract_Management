package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �����ͬ����Աҳ���Servlet 
 */
public class ToOperatorServlet extends HttpServlet {

	/**
	 * ��ת������Աҳ��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		
		// �����Ự
		HttpSession session = null;
		// ��ûỰ 
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//  ����û�û�е�¼����ת����½����
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// Forward����ͬ����Աҳ��
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
