package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import utils.AppException;
/**
 * 处理注册请求的Servlet
 */
public class RegisterServlet extends HttpServlet {

	/**
	 * 处理注册请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设定编码方式
		request.setCharacterEncoding("UTF-8");
		//获得用户名和密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//声明代表注册的flag
		boolean flag = false;
		//初始化message
		String message = "";
		try {
			//初始化用户实体
			User user = new User();
			// 初始化逻辑层
			UserService userService = new UserService();
			// 封装用户信息
			user.setName(name);
			user.setPassword(password);
			// 调用逻辑层处理
			flag = userService.register(user);
			if (flag) {
				// 注册成功后重定向到登录页面
				message = "注册成功";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/toLogin").forward(request,
						response);
			} else {
				// 初始化message
				message = "注册失败";
				request.setAttribute("message", message); // Save prompt message into request 
				// Forward 到注册页面
				request.getRequestDispatcher("/register.jsp").forward(request,
						response);
			}
		} catch (AppException e) {
			e.printStackTrace();
			// 重定向跳转到异常页面
			response.sendRedirect("toError");
		}
	}

	/**
	 * 处理注册请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用doPost处理请求
		doPost(request, response);
	}
	
	
}
