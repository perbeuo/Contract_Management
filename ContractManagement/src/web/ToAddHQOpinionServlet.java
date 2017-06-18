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
 * 进入会签界面的Servlet 
 */
public class ToAddHQOpinionServlet extends HttpServlet {

	/**
	 * 跳转到会签界面
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");
		
		// 声明会话
		HttpSession session = null;
		// 获得会话
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 如果用户没有登录则跳转到登陆界面
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			// 获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));

			try {
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				// Query contract information according to Contract id
				Contract contract = contractService.getContract(conId);

				// 将合同传递到request
				request.setAttribute("contract", contract);
				//  forward到会签页面
				request.getRequestDispatcher("/addHQOpinion.jsp").forward(
						request, response);
			} catch (AppException e) {
				e.printStackTrace();
				// 重定向跳转到异常页面
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 * 处理GET请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用doPost方法来处理请求
		this.doPost(request, response);
	}
}
