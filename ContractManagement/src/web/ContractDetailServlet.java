package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConDetailBusiModel;
import service.ContractService;
import utils.AppException;

/**
 * 用来查询合同信息的Servlet
 */
public class ContractDetailServlet extends HttpServlet {

	/**
	 * 跳转到合同信息页面
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
				// 根据合同ID查询合同信息
				ConDetailBusiModel conDetailBusiModel = contractService.getContractDetail(conId);
				// 将合同传递到request
				request.setAttribute("conDetailBusiModel", conDetailBusiModel);
				// forward到会签页面
				request.getRequestDispatcher("/contractDetail.jsp").forward(
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
