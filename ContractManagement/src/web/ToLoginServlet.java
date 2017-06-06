package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user's request,output login page
 */
public class ToLoginServlet extends HttpServlet {

	/**
	 *  Process the POST requests,output login page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * Process the GET requests, output login page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");//Set the types of output content 
		response.setCharacterEncoding("UTF-8");//Set the character encoding of output content
		PrintWriter out = response.getWriter();// Get the output object
		// Output the <!DOCTYPE> declaration
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		// Output the standard HTML structure
		out.println("<html>\r\n");
		out.println("<head>\r\n");
		// Set the character encoding for the HTML document
		out.println("<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />\r\n");
		out.println("<title>Login page</title>\r\n");
		out.println("</head>\r\n");
		out.println("<body>\r\n");
		out.println("<form action=\"login\" method=\"post\">\r\n");//form
		out.println("User name:<input type=\"text\" name=\"name\" ");
		/*
		 * After login fails, get user name in the input box for display
		 */ 
		// Complete user name input box: <input type="text" name="" value="get user name for display" />
		out.print("value=\"");
		if (request.getAttribute("userName") != null) {
			out.print(request.getAttribute("userName"));
		}
		out.println("\"/> <br />\r\n");
		
		out.println("Password:<input type=\"password\" name=\"password\" /> <br />\r\n");
		out.println("<br />\r\n");
		// Output message
		if (request.getAttribute("message") != null) {
			out.print("<font color=\"red\"> ");
		    out.print(request.getAttribute("message"));
		    out.print("</font> <br />\r\n");
		    out.println("<br />\r\n");
		}
		out.println("<input type=\"submit\" value=\"Login\" /> <br />\r\n");
		out.println("</form>\r\n");
		out.println("</body>\r\n");
		out.println("</html>\r\n");
		// Clear and close the output stream
		out.flush();
		out.close();
	}
}


