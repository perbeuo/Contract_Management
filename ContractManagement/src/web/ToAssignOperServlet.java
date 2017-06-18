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
 * 进入分配操作员页面的Servlet
 */
public class ToAssignOperServlet extends HttpServlet {

	/**
	 * 跳转到分配操作员页面
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
			// 根据id查询合同
			Contract contract = contractService.getContract(conId);
			
			// 初始化userService
			UserService userService = new UserService();
			
			/*
			 * 获得有 "countersign", "approve", "sign" 等权限的用户保存到userlist
			 * 操作员有上述权限, 设置操作员的角色代码为2
			 * RoleId = 2 对应有上述权限的人
			 */
			int roleId = 2;
			// 初始化 userList
			List<User> userList = new ArrayList<User>();
			// 通过roleid获得用户
			userList = userService.getUserListByRoleId(roleId);
			
			// 将合同传递到request
			request.setAttribute("contract", contract);
			// 保存userList到request
			request.setAttribute("userList", userList);
			// Forward 到分配操作员页面
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
