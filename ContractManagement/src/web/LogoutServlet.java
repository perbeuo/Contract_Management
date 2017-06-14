package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �����û�ע����Servlet
 */
public class LogoutServlet extends HttpServlet{

	/**
	 * ����ע������
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �����Ự
		HttpSession session = null;
		// ��ûỰ object
		session = request.getSession();
		// �ӻỰ��ɾ����Ϣ
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		// �ض��򵽵�¼����
		response.sendRedirect("toLogin");
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
