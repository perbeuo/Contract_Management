package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Right;
import service.UserService;
import utils.AppException;

/**
 * 分配权限的Servlet
 */
public class AssignPermServlet extends HttpServlet {

	/**
	 * 处理分配权限的请求
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
			 * 获得权限分配信息
			 */
			// 获得分配的用户ID
			int uId = Integer.parseInt(request.getParameter("userId"));
			// 获得角色ID
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// 创建权限实体并赋值
			Right right = new Right();
			right.setUserId(uId);
			right.setRoleId(roleId);
			
			try {
				// 初始化userService
				UserService userService = new UserService();
				// 调用逻辑层来分配权限
				userService.assignPermission(right);
				
				// 分配后重定向到分配页
				response.sendRedirect("toYhqxList");
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
