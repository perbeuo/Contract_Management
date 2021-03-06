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
import service.UserService;
import utils.AppException;

/**
 * 进入用户权限列表
 */
public class ToYhqxListServlet extends HttpServlet{

	/**
	 * 跳转到用户权限页面
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
				// 初始化userService
				UserService userService = new UserService();
				// 初始化permissionList
				List<PermissionBusiModel> permissionList = new ArrayList<PermissionBusiModel>();
				// 调用逻辑层 来获得用户权限列表
				permissionList = userService.getYhqxList();
				// 保存newUserList到request
				request.setAttribute("permissionList", permissionList);
				// Forward到用户权限页面
				request.getRequestDispatcher("/yhqxList.jsp").forward(request, response);
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
