package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import service.ContractService;
import utils.AppException;

/**
 *  ���������ͬ��Servlet
 */
public class DgContractServlet extends HttpServlet {

	/**
	 * ���� �����ͬ������
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
			// ��ú�ͬ��Ϣ
			int conId = Integer.parseInt(request.getParameter("conId"));
			String name = request.getParameter("name");
			String customer = request.getParameter("customer");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String content = request.getParameter("content");

			// ����java.util.Date�������͵Ķ���
			Date begin = new Date();
			Date end = new Date();

			// ����һ�����ڸ�ʽ���󣬽�ʱ���ַ���ת��
			// �� java.util.Date��������
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				begin = dateFormat.parse(beginTime);
				end = dateFormat.parse(endTime);

				// ������ͬ��������������
				Contract contract = new Contract();
				contract.setId(conId);
				contract.setName(name);
				contract.setCustomer(customer);
				contract.setBeginTime(begin);
				contract.setEndTime(end);
				contract.setContent(content);
				contract.setUserId(userId);

				// ��ʼ����ͬ�������
				ContractService contractService = new ContractService();
				// �����߼����� �����ͬ
				contractService.finalize(contract);

				// ������ض��򵽶���ǰ��ҳ��
				response.sendRedirect("toDdghtList");
			} catch (ParseException e) {
				e.printStackTrace();
				// ��ʼ��message
				String message = "";
				message = "��������ȷ�����ڸ�ʽ��";
				// ����message������
				request.setAttribute("message", message);
				// forward������ҳ��
				request.getRequestDispatcher("/dgContract.jsp").forward(
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
