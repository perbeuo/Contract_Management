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
 * 进入签订页面的Servlet
 */
public class ToAddQDInfoServlet extends HttpServlet {

	/**
	 * 跳转到页面
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
				// 初始化contractService
				ContractService contractService = new ContractService();
				// 通过合同ID查询合同信息
				Contract contract = contractService.getContract(conId);

				// 保存合同到请求
				request.setAttribute("contract", contract);
				// Forward到签订页面
				request.getRequestDispatcher("/addQDInformation.jsp").forward(
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
