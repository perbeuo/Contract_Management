package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConBusiModel;
import service.ContractService;
import utils.AppException;

/**
 * �����������ͬҳ��
 */
public class ToDshphtListServlet extends HttpServlet{

	/**
	 * ��ת��������ҳ��
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
				// ��ʼ��contractService
				ContractService contractService = new ContractService();
				// ��ʼ��contractList
				List<ConBusiModel> contractList = new ArrayList<ConBusiModel>();
				// �����߼�������ô�������ͬ�б�
				contractList = contractService.getDshphtList(userId);
				// ����contractList��request
				request.setAttribute("contractList", contractList);
				// Forward����������ͬҳ��
				request.getRequestDispatcher("/dshphtList.jsp").forward(request, response);
			} catch (AppException e) {
				e.printStackTrace();
				//�ض����쳣ҳ��
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