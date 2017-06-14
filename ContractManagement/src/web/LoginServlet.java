package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;
import service.UserService;
import utils.AppException;
/**
 * �����û���¼��Servlet 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * �����¼����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		// ����û���������
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// ��ʼ��message
		String message = "";
		
		int userId = -1;
		try {
			// ��ʼ���߼���
			UserService userService = new UserService();
			// �����߼��㴦���¼
			userId = userService.login(name, password);
			if (userId > 0) { // ��½�ɹ�
				// �����Ự
				HttpSession session = null;
				// ��ûỰ
				session = request.getSession();
				// �����û�ID�����뵽�Ự
				session.setAttribute("userId", userId);
				session.setAttribute("userName", name);
				// ������ɫ
				Role role = null;
				//�����߼����� ��ý�ɫ��Ϣ
				role = userService.getUserRole(userId);
				// ͨ���жϽ�ɫ�����ת����ͬҳ��
				if ( role == null) {
					//�ض������û�����
					response.sendRedirect("toNewUser");
				} else if (role.getName().equals("admin")) {
					//�ض��򵽹���Ա����
					response.sendRedirect("toAdmin");
				} else if (role.getName().equals("operator")) {
					//�ض��򵽲����߽���
					response.sendRedirect("toOperator");
				}
			} else {// ��½ʧ��
				// ����message��Ϣ
				message = "�û������������";
				request.setAttribute("message", message); // ����message������
				// �����û���������
				request.setAttribute("userName", name);	
				// Forward ����½ҳ��
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// �ض�����ת���쳣ҳ��
			response.sendRedirect("toError");
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
