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
 * ������ͬ��servlet
 */
public class AddSHPOpinionServlet extends HttpServlet {

	/**
	 * ����������ͬ��Post����
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");

		// �����Ự
		HttpSession session = null;
		//  ��ûỰ
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// ����û�û�е�¼����ת����½����
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			//  ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// ������
			String content = request.getParameter("content");
			// �����������
			String approve = request.getParameter("approve");

			// ʵ���� conProcess ����װ������Ϣ
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);
			
			// ���������������״ֵ̬
			if (approve.equals("true")) { // Approve type is "pass"
				// ����"PROCESS_APPROVE"�����״̬��Ϣ"DONE"
				conProcess.setState(Constant.DONE);
			} else { //  Approve type is "refuse"
				// ����"PROCESS_APPROVE" �����״̬��Ϣ"VETOED"
				conProcess.setState(Constant.VETOED);
			}

			/*
			 * �����߼���������
			 */
			try {
				//  ��ʼ��contractService
				ContractService contractService = new ContractService();
				//�����߼�����������ͬ
				contractService.approve(conProcess);

				// ������ͬ���ض��򵽴�������ͬҳ��
				response.sendRedirect("toDshphtList");
			} catch (AppException e) {
				e.printStackTrace();
				// �ض����쳣ҳ��
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 *����GET����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����doPost()��������������
		this.doPost(request, response);
	}
}