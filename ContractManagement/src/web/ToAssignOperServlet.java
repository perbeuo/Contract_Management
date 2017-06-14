package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import model.User;
import service.ContractService;
import service.UserService;
import utils.AppException;

/**
 * Servlet for accessing operator assignment page
 */
public class ToAssignOperServlet extends HttpServlet {

	/**
	 * Jump to operator assignment page
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
			// 获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));
			
			try {
			// 初始化合同服务对象
			ContractService contractService = new ContractService();
			// Query contract information according to contract id
			Contract contract = contractService.getContract(conId);
			
			// Initialize userService
			UserService userService = new UserService();
			
			/*
			 * Need to get user list with "countersign", "approve", "sign" permissions 
			 * Operator role have above rights, here using hard-coding way to set operator role's role id to 2,
			 * RoleId = 2 corresponding to the role's primary key who have rights as above in contract rights table
			 */
			int roleId = 2;
			// Initialize userList
			List<User> userList = new ArrayList<User>();
			// Get user list according to role id
			userList = userService.getUserListByRoleId(roleId);
			
			// 将合同传递到request
			request.setAttribute("contract", contract);
			// Save userList to request
			request.setAttribute("userList", userList);
			// Forward to operator assignment page
			request.getRequestDispatcher("/assignOperator.jsp").forward(request,
					response);
			} catch (AppException e) {
				e.printStackTrace();
				//重定向跳转到异常页面
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
