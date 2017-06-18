package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contract;
import service.ContractService;
import utils.AppException;

/**
 * 用来起草合同的Servlet
 */
public class DraftServlet extends HttpServlet {

	/**
	 * 处理起草合同的请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设定编码方式
		request.setCharacterEncoding("UTF-8");
		
		// 声明会话
		HttpSession session = null;
		// 获得会话 对象
		session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		 
		// 如果用户没有登录则跳转到登陆界面
		if (userId == null) {
			response.sendRedirect("toLogin");
		}else {
			// 获得合同信息
			String name = request.getParameter("name");
			String customer = request.getParameter("customer");
			String content = request.getParameter("content");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			
			//  创建java.util.Date type类型对象
			Date begin = new Date();
			Date end = new Date();
			
			// 定义一个日期格式对象，将时间字符串转换 到 java.util.Date数据类型 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			// 初始化message
			String message = "";
			
			try {
				begin = dateFormat.parse(beginTime);
				end = dateFormat.parse(endTime);
				
				// 创建合同对象来保存属性 
				Contract contract = new Contract();
				contract.setName(name);
				contract.setCustomer(customer);
				contract.setBeginTime(begin);
				contract.setEndTime(end);
				contract.setContent(content);
				contract.setUserId(userId);
				
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				
				// 回到起草页面，显示信息
				if (contractService.draft(contract)) {
					message = "起草成功！";
					// 传递message
					request.setAttribute("contract", contract);
				} else {
					message = "起草失败！";
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				message = "需要正确的日期信息！";
			} catch (AppException e) {
				e.printStackTrace();
				//重定向跳转到异常页面
				response.sendRedirect("toError");
				return;
			}
			// 保存message到请求
			request.setAttribute("message", message);
			// Forward 到起草界面
			request.getRequestDispatcher("/addContract.jsp").forward(request, response);
		}
	}
	
	/**
	 * 处理GET请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用doPost方法来处理请求
		doPost(request,response);
	}

}
