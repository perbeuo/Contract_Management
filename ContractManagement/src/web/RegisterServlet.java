package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import utils.AppException;
/**
 * ����ע�������Servlet
 */
public class RegisterServlet extends HttpServlet {

	/**
	 * ����ע������
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		//����û���������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//��������ע���flag
		boolean flag = false;
		//��ʼ��message
		String message = "";
		try {
			//��ʼ���û�ʵ��
			User user = new User();
			// ��ʼ���߼���
			UserService userService = new UserService();
			// ��װ�û���Ϣ
			user.setName(name);
			user.setPassword(password);
			// �����߼��㴦��
			flag = userService.register(user);
			if (flag) {
				// ע��ɹ����ض��򵽵�¼ҳ��
				message = "ע��ɹ�";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/toLogin").forward(request,
						response);
			} else {
				// ��ʼ��message
				message = "ע��ʧ��";
				request.setAttribute("message", message); // Save prompt message into request 
				// Forward ��ע��ҳ��
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// �ض�����ת���쳣ҳ��
			response.sendRedirect("toError");
		}
	}

	/**
	 * ����ע������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����doPost��������
		doPost(request, response);
	}
	
	
}
