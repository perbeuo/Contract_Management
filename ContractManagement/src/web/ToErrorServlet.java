package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Output exception user page
 */
public class ToErrorServlet extends HttpServlet {

	/**
	 * Process POST request, output exception user page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to Process request
		doGet(request, response); 
	}

	/**
	 * Process GET request, output exception user page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");//Set the types of output content  
		response.setCharacterEncoding("UTF-8");// Set the character encoding of output content
		PrintWriter out = response.getWriter();//Get the output object
		// Output the <!DOCTYPE> declaration
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		// Output the standard HTML structure
		out.println("<html>\r\n");
		out.println("<head>\r\n");
		// Set the character set encoding for the HTML document
		out.println("<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />\r\n");
		out.println("<title>Exception page</title>\r\n");
		out.println("</head>\r\n");
		out.println("<body>\r\n");
		out.println("<br />\r\n");
		// Output exception prompt message
		out.println("System is under maintenance...");
		out.println("</body>\r\n");
		out.println("</html>\r\n");
		// Clear and close the output stream
		out.flush();
		out.close();
	}
}


