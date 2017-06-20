package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PermissionBusiModel;
import model.Role;
import service.UserService;
import utils.AppException;

/**
 * �����������ҳ���Servlet
 */
public class ToAssignPermServlet extends HttpServlet {

	/**
	 * ��ת���������ҳ��
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
		
		// ����û�û�е�¼����ת����½����
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			// ����û�ID���û�������ɫID
			int uId = Integer.parseInt(request.getParameter("userId"));
			String userName = (String)request.getParameter("uName");
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// �����û����ID��PermissionBusiModel
			//��ʼ��PermissionBusiModel
			PermissionBusiModel permission = new PermissionBusiModel();
			permission.setUserId(uId);
			permission.setUserName(userName);
			permission.setRoleId(roleId);
			
			// ����permission��request
			request.setAttribute("permission", permission);
			
			try {
				// ��ʼ��userService
				UserService userService = new UserService();
				// ��ʼ��roleList
				List<Role> roleList = new ArrayList<Role>();
				// �����߼�������ý�ɫ�б�
				roleList = userService.getRoleList();

				// ����roleList��request
				request.setAttribute("roleList", roleList);
				// Forward���������ҳ��
				request.getRequestDispatcher("/assignPermission.jsp").forward(
						request, response);
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
