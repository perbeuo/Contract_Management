package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConProcess;
import service.ContractService;
import utils.AppException;
import utils.Constant;

/**
 * ǩ����ͬ��Servlet
 */
public class AddQDInfoServlet extends HttpServlet {

	/**
	 * ����ǩ����ͬPost����
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

			// ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// ���ǩ��ѡ��
			String content = request.getParameter("content");

			// ��ʼ��conProcess����װǩ����Ϣ
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);

			/*
			 * �����߼���
			 */
			try {
				//  ��ʼ��contractService
				ContractService contractService = new ContractService();
				// �����߼��� ��ǩ����ͬ
				contractService.sign(conProcess);

				// ǩ�����ض��򵽴�ǩ����ͬҳ��
				response.sendRedirect("toDqdhtList");
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