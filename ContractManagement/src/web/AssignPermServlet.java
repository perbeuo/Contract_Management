package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Right;
import service.UserService;
import utils.AppException;

/**
 * ����Ȩ�޵�Servlet
 */
public class AssignPermServlet extends HttpServlet {

	/**
	 * �������Ȩ�޵�����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");

		// �����Ự
		HttpSession session = null;
		// ��ûỰ
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// ����û�û�е�¼����ת����½����
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			/*
			 * ���Ȩ�޷�����Ϣ
			 */
			// ��÷�����û�ID
			int uId = Integer.parseInt(request.getParameter("userId"));
			// ��ý�ɫID
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// ����Ȩ��ʵ�岢��ֵ
			Right right = new Right();
			right.setUserId(uId);
			right.setRoleId(roleId);
			
			try {
				// ��ʼ��userService
				UserService userService = new UserService();
				// �����߼���������Ȩ��
				userService.assignPermission(right);
				
				// ������ض��򵽷���ҳ
				response.sendRedirect("toYhqxList");
			} catch (AppException e) {
				e.printStackTrace();
				// �ض�����ת���쳣ҳ��
				response.sendRedirect("toError");
			}
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
