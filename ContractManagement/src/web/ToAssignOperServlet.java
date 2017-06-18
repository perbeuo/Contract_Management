package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import model.User;
import service.ContractService;
import service.UserService;
import utils.AppException;

/**
 * ����������Աҳ���Servlet
 */
public class ToAssignOperServlet extends HttpServlet {

	/**
	 * ��ת���������Աҳ��
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
			// ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));
			
			try {
			// ��ʼ����ͬ�������
			ContractService contractService = new ContractService();
			// ����id��ѯ��ͬ
			Contract contract = contractService.getContract(conId);
			
			// ��ʼ��userService
			UserService userService = new UserService();
			
			/*
			 * ����� "countersign", "approve", "sign" ��Ȩ�޵��û����浽userlist
			 * ����Ա������Ȩ��, ���ò���Ա�Ľ�ɫ����Ϊ2
			 * RoleId = 2 ��Ӧ������Ȩ�޵���
			 */
			int roleId = 2;
			// ��ʼ�� userList
			List<User> userList = new ArrayList<User>();
			// ͨ��roleid����û�
			userList = userService.getUserListByRoleId(roleId);
			
			// ����ͬ���ݵ�request
			request.setAttribute("contract", contract);
			// ����userList��request
			request.setAttribute("userList", userList);
			// Forward ���������Աҳ��
			request.getRequestDispatcher("/assignOperator.jsp").forward(request,
					response);
			} catch (AppException e) {
				e.printStackTrace();
				//�ض�����ת���쳣ҳ��
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
