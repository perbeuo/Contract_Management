package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CSignatureOpinion;
import service.ContractService;
import utils.AppException;

/**
 * Servlet for display countersign opinion
 */
public class ShowHQOpinionServlet extends HttpServlet {

	/**
	 * Process Post requests of displaying countersign opinion
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
			//  获得合同ID
			int conId = Integer.parseInt(request.getParameter("conId"));
			
			try {
				// 初始化合同服务对象
				ContractService contractService = new ContractService();
				//CSignatureOpinion CSignatureOpinion = new CSignatureOpinion();
				// Initialize csOpinionList
				List<CSignatureOpinion> csOpinionList = new ArrayList<CSignatureOpinion>();
				// 调用逻辑层来 get countersign opinion
				csOpinionList = contractService.showHQOpinion(conId);
				// Save conProcessList to request
				request.setAttribute("csOpinionList", csOpinionList);
				// Forward to countersign opinion page
				request.getRequestDispatcher("/showHQOpinion.jsp").forward(request,
						response);
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