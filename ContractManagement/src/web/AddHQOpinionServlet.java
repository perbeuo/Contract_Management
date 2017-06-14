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

/**
 * 用来会签合同的Servlet
 */
public class AddHQOpinionServlet extends HttpServlet {

	/**
	 * 处理Post会签请求
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
			// 获得会签意见
			String content = request.getParameter("content");
			
			// 创建ConProcess对象来封装信息
			ConProcess conProcess = new ConProcess();
			conProcess.setConId(conId);
			conProcess.setUserId(userId);
			conProcess.setContent(content);
			
			try {
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				// 调用逻辑层来处理会签
				contractService.counterSign(conProcess);
				
				// 会签后重定向到提示已会签的界面
				response.sendRedirect("toDhqhtList");

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
