package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ContractService;
import utils.AppException;
import utils.Constant;

/**
 * ���������ͬ��Servlet
 */
public class AssignOperServlet extends HttpServlet {

	/**
	 * ��������ͬ�Ľ��
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
			 * ��ô���ǩ��ͬ��Ϣ
			 */
			// ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// Get assigned cuntersign people's id
			String[] hqht = request.getParameterValues("hqht");
			// Get assigned approver's id
			String[] spht = request.getParameterValues("spht");
			// Get assigned signer's id
			String[] qdht = request.getParameterValues("qdht");

			try {
				// ��ʼ����ͬ�������
				ContractService contractService = new ContractService();
				/*
				 * �����߼����������ͬ
				 */
				// �����ǩ��
				for (String hq : hqht) {
					contractService.distribute(conId, Integer.parseInt(hq),
							Constant.PROCESS_CSIGN);
				}

				// ����������
				for (String sp : spht) {
					contractService.distribute(conId, Integer.parseInt(sp),
							Constant.PROCESS_APPROVE);
				}

				// ����ǩ����
				for (String qd : qdht) {
					contractService.distribute(conId, Integer.parseInt(qd),
							Constant.PROCESS_SIGN);
				}

				// ��ɷ�����ض���֮ǰ��ҳ��
				response.sendRedirect("toDfphtList");
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
