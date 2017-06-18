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
 * �����ǩ�����Servlet 
 */
public class ToAddHQOpinionServlet extends HttpServlet {

	/**
	 * ��ת����ǩ����
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
				// ��ʼ����ͬ�������
				ContractService contractService = new ContractService();
				// Query contract information according to Contract id
				Contract contract = contractService.getContract(conId);

				// ����ͬ���ݵ�request
				request.setAttribute("contract", contract);
				//  forward����ǩҳ��
				request.getRequestDispatcher("/addHQOpinion.jsp").forward(
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
