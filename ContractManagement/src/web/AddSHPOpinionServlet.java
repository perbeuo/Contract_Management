package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConProcess;
import service.ContractService;
import utils.AppException;
import utils.Constant;

/**
 * 审批合同的servlet
 */
public class AddSHPOpinionServlet extends HttpServlet {

	/**
	 * 处理审批合同的Post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");

		// 声明会话
		HttpSession session = null;
		//  获得会话
		session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		// 如果用户没有登录则跳转到登陆界面
		if (userId == null) {
			response.sendRedirect("toLogin");
		} else {

			//  获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// 获得意见
			String content = request.getParameter("content");
			// 获得审批操作
			String approve = request.getParameter("approve");

			// 实例化 conProcess 来封装审批信息
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);
			
			// 根据审批意见设置状态值
			if (approve.equals("true")) { // Approve type is "pass"
				// 设置"PROCESS_APPROVE"类根据状态信息"DONE"
				conProcess.setState(Constant.DONE);
			} else { //  Approve type is "refuse"
				// 设置"PROCESS_APPROVE" 类根据状态信息"VETOED"
				conProcess.setState(Constant.VETOED);
			}

			/*
			 * 调用逻辑层来处理
			 */
			try {
				//  初始化contractService
				ContractService contractService = new ContractService();
				//调用逻辑层来审批合同
				contractService.approve(conProcess);

				// 审批合同后，重定向到待审批合同页面
				response.sendRedirect("toDshphtList");
			} catch (AppException e) {
				e.printStackTrace();
				// 重定向到异常页面
				response.sendRedirect("toError");
			}
		}
	}

	/**
	 *处理GET请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用doPost()方法来处理请求
		this.doPost(request, response);
	}
}