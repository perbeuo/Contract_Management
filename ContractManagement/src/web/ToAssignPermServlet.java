package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PermissionBusiModel;
import model.Role;
import service.UserService;
import utils.AppException;

/**
 * 进入许可配置页面的Servlet
 */
public class ToAssignPermServlet extends HttpServlet {

	/**
	 * 跳转到许可配置页面
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

			// 获得用户ID，用户名，角色ID
			int uId = Integer.parseInt(request.getParameter("userId"));
			String userName = (String)request.getParameter("uName");
			int roleId = Integer.parseInt(request.getParameter("roleId"));
			// 保存用户许可ID到PermissionBusiModel
			//初始化PermissionBusiModel
			PermissionBusiModel permission = new PermissionBusiModel();
			permission.setUserId(uId);
			permission.setUserName(userName);
			permission.setRoleId(roleId);
			
			// 保存permission到request
			request.setAttribute("permission", permission);
			
			try {
				// 初始化userService
				UserService userService = new UserService();
				// 初始化roleList
				List<Role> roleList = new ArrayList<Role>();
				// 调用逻辑层来获得角色列表
				roleList = userService.getRoleList();

				// 保存roleList到request
				request.setAttribute("roleList", roleList);
				// Forward到许可配置页面
				request.getRequestDispatcher("/assignPermission.jsp").forward(
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
