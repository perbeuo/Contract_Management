package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import service.ContractService;
import utils.AppException;

/**
 * ��������ҳ���Servlet
 */
public class ToAddSHPOpinionServlet extends HttpServlet {

	/**
	 * ��ת������ҳ��
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

			// ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));

			try {
				// ��ʼ��contractService
				ContractService contractService = new ContractService();
				// ���ݺ�ͬID��ѯ��ͬ��Ϣ
				Contract contract = contractService.getContract(conId);

				// ����contract��request
				request.setAttribute("contract", contract);
				// Forward������ҳ��
				request.getRequestDispatcher("/addSHPOpinion.jsp").forward(
						request, response);
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
