package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;
import service.UserService;
import utils.AppException;
/**
 * 处理用户登录的Servlet 
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 处理登录请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设定编码方式
		request.setCharacterEncoding("UTF-8");
		// 获得用户名和密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		// 初始化message
		String message = "";
		
		int userId = -1;
		try {
			// 初始化逻辑层
			UserService userService = new UserService();
			// 调用逻辑层处理登录
			userId = userService.login(name, password);
			if (userId > 0) { // 登陆成功
				// 声明会话
				HttpSession session = null;
				// 获得会话
				session = request.getSession();
				// 保存用户ID和密码到会话
				session.setAttribute("userId", userId);
				session.setAttribute("userName", name);
				// 声明角色
				Role role = null;
				//调用逻辑层来 获得角色信息
				role = userService.getUserRole(userId);
				// 通过判断角色身份跳转到不同页面
				if ( role == null) {
					//重定向到新用户界面
					response.sendRedirect("toNewUser");
				} else if (role.getName().equals("admin")) {
					//重定向到管理员界面
					response.sendRedirect("toAdmin");
				} else if (role.getName().equals("operator")) {
					//重定向到操作者界面
					response.sendRedirect("toOperator");
				}
			} else {// 登陆失败
				// 设置message信息
				message = "用户名或密码错误！";
				request.setAttribute("message", message); // 保存message到请求
				// 保存用户名到请求
				request.setAttribute("userName", name);	
				// Forward 到登陆页面
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// 重定向跳转到异常页面
			response.sendRedirect("toError");
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
