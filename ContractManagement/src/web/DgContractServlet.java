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
 *  用来定稿合同的Servlet
 */
public class DgContractServlet extends HttpServlet {

	/**
	 * 处理 定稿合同的请求
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
			// 获得合同信息
			int conId = Integer.parseInt(request.getParameter("conId"));
			String name = request.getParameter("name");
			String customer = request.getParameter("customer");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");
			String content = request.getParameter("content");

			// 创建java.util.Date数据类型的对象
			Date begin = new Date();
			Date end = new Date();

			// 定义一个日期格式对象，将时间字符串转换
			// 到 java.util.Date数据类型
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				begin = dateFormat.parse(beginTime);
				end = dateFormat.parse(endTime);

				// 创建合同对象来保存属性
				Contract contract = new Contract();
				contract.setId(conId);
				contract.setName(name);
				contract.setCustomer(customer);
				contract.setBeginTime(begin);
				contract.setEndTime(end);
				contract.setContent(content);
				contract.setUserId(userId);

				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				// 调用逻辑层来 定稿合同
				contractService.finalize(contract);

				// 定稿后重定向到定稿前的页面
				response.sendRedirect("toDdghtList");
			} catch (ParseException e) {
				e.printStackTrace();
				// 初始化message
				String message = "";
				message = "请输入正确的日期格式！";
				// 保存message到请求
				request.setAttribute("message", message);
				// forward到定稿页面
				request.getRequestDispatcher("/dgContract.jsp").forward(
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
