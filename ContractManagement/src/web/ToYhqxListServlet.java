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
import service.UserService;
import utils.AppException;

/**
 * �����û�Ȩ���б�
 */
public class ToYhqxListServlet extends HttpServlet{

	/**
	 * ��ת���û�Ȩ��ҳ��
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
		}else {
			
			try {
				// ��ʼ��userService
				UserService userService = new UserService();
				// ��ʼ��permissionList
				List<PermissionBusiModel> permissionList = new ArrayList<PermissionBusiModel>();
				// �����߼��� ������û�Ȩ���б�
				permissionList = userService.getYhqxList();
				// ����newUserList��request
				request.setAttribute("permissionList", permissionList);
				// Forward���û�Ȩ��ҳ��
				request.getRequestDispatcher("/yhqxList.jsp").forward(request, response);
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
