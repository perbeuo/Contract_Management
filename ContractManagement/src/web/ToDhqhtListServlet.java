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
 * 进入待会签合同页面
 */
public class ToDhqhtListServlet extends HttpServlet{

	/**
	 * 跳转到待会签合同页面
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
		}else {
			
			try {
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				// 初始化contractList
				List<ConBusiModel> contractList = new ArrayList<ConBusiModel>();
				// 调用逻辑层来 获得待会签合同列表
				contractList = contractService.getDhqhtList(userId);
				// 保存 contractList到 request
				request.setAttribute("contractList", contractList);
				// Forward 到待会签合同页面
				request.getRequestDispatcher("/dhqhtList.jsp").forward(request, response);
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
