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
 * 签订合同的Servlet
 */
public class AddQDInfoServlet extends HttpServlet {

	/**
	 * 处理签订合同Post请求
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

			// 获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));
			// 获得签订选项
			String content = request.getParameter("content");

			// 初始化conProcess来封装签订信息
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);

			/*
			 * 调用逻辑层
			 */
			try {
				//  初始化contractService
				ContractService contractService = new ContractService();
				// 调用逻辑层 来签订合同
				contractService.sign(conProcess);

				// 签订后重定向到待签订合同页面
				response.sendRedirect("toDqdhtList");
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