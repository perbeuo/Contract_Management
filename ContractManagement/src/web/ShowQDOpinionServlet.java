package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CSignatureOpinion;
import service.ContractService;
import utils.AppException;

/**
 * ��ʾǩ������� Servlet 
 */
public class ShowQDOpinionServlet extends HttpServlet {

	/**
	 * ������ʾǩ�����������
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
			//  ��ú�ͬID
			int conId = Integer.parseInt(request.getParameter("conId"));
			
			try {
				// ��ʼ����ͬ�������
				ContractService contractService = new ContractService();
				// ��ʼ��csOpinionList
				List<CSignatureOpinion> csOpinionList = new ArrayList<CSignatureOpinion>();
				// �����߼��������ǩ�����
				csOpinionList = contractService.showQDOpinion(conId);
				// ����ǩ�����������
				request.setAttribute("csOpinionList", csOpinionList);
				// Forward ��ǩ���������
				request.getRequestDispatcher("/showQDOpinion.jsp").forward(request,
						response);
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