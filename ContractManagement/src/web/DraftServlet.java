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
 * ������ݺ�ͬ��Servlet
 */
public class DraftServlet extends HttpServlet {

	/**
	 * ������ݺ�ͬ������
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�趨���뷽ʽ
		request.setCharacterEncoding("UTF-8");
		
		// �����Ự
		HttpSession session = null;
		// ��ûỰ ����
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		 
		// ����û�û�е�¼����ת����½����
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// ��ú�ͬ��Ϣ
			String name = request.getParameter("name");
			String customer = request.getParameter("customer");
			String content = request.getParameter("content");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			//  ����java.util.Date type���Ͷ���
			Date begin = new Date();
			Date end = new Date();
			
			// ����һ�����ڸ�ʽ���󣬽�ʱ���ַ���ת�� �� java.util.Date�������� 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			// ��ʼ��message
			String message = "";
			
			try {
				begin = dateFormat.parse(beginTime);
				end = dateFormat.parse(endTime);
				
				// ������ͬ�������������� 
				Contract contract = new Contract();
				contract.setName(name);
				contract.setCustomer(customer);
				contract.setBeginTime(begin);
				contract.setEndTime(end);
				contract.setContent(content);
				contract.setUserId(userId);
				
				// ��ʼ����ͬ�������
				ContractService contractService = new ContractService();
				
				// �ص����ҳ�棬��ʾ��Ϣ
				if (contractService.draft(contract)) {
					message = "��ݳɹ���";
					// ����message
					request.setAttribute("contract", contract);
				} else {
					message = "���ʧ�ܣ�";
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				message = "��Ҫ��ȷ��������Ϣ��";
			} catch (AppException e) {
				e.printStackTrace();
				//�ض�����ת���쳣ҳ��
				response.sendRedirect("toError");
				return;
			}
			// ����message������
			request.setAttribute("message", message);
			// Forward ����ݽ���
			request.getRequestDispatcher("/addContract.jsp").forward(request, response);
		}
	}
	
	/**
	 * ����GET����
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����doPost��������������
		doPost(request,response);
	}

}
