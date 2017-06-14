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
 * 用来分配合同的Servlet
 */
public class AssignOperServlet extends HttpServlet {

	/**
	 * 处理分配合同的结果
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");

		// 声明会话
		HttpSession session = null;
		// 获得会话
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// 如果用户没有登录则跳转到登陆界面
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			/*
			 * 获得待会签合同信息
			 */
			// 获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// Get assigned cuntersign people's id
			String[] hqht = request.getParameterValues("hqht");
			// Get assigned approver's id
			String[] spht = request.getParameterValues("spht");
			// Get assigned signer's id
			String[] qdht = request.getParameterValues("qdht");

			try {
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				/*
				 * 调用逻辑层来分配合同
				 */
				// 分配会签者
				for (String hq : hqht) {
					contractService.distribute(conId, Integer.parseInt(hq),
							Constant.PROCESS_CSIGN);
				}

				// 分配审批者
				for (String sp : spht) {
					contractService.distribute(conId, Integer.parseInt(sp),
							Constant.PROCESS_APPROVE);
				}

				// 分配签订者
				for (String qd : qdht) {
					contractService.distribute(conId, Integer.parseInt(qd),
							Constant.PROCESS_SIGN);
				}

				// 完成分配后，重定向到之前的页面
				response.sendRedirect("toDfphtList");
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
