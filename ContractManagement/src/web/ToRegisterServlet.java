package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Process user's request, output the target page
 */
public class ToRegisterServlet extends HttpServlet {

	/**
	 * Process the POST requests, output the target page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Call doGet() to process request
		doGet(request, response);
	}

	/**
	 * Process the GET requests, output the target page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");// Set the types of output content 
		response.setCharacterEncoding("UTF-8");// Set the character encoding of output content
		PrintWriter out = response.getWriter();// Get the output object
		// Output the <!DOCTYPE> declaration
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		// Output the standard HTML structure
		out.println("<html>\r\n");
		out.println("<head>\r\n");
		// Set the character encoding for the HTML document
		out.println("<meta http-equiv='Content-Type' content='text/html' charset='utf-8' />\r\n");
		out.println("<title>Registration pag</title>\r\n");
		out.println("</head>\r\n");
		out.println("<body>\r\n");
		out.println("<form action=\"register\" method=\"post\">\r\n");//form
		out.println("&nbsp;&nbsp;&nbsp;User &nbsp; name锛�&nbsp;<input type=\"text\" name=\"name\" /> <br />\r\n");
		out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password锛�&nbsp;&nbsp;<input type=\"password\" name=\"password\" /> <br />\r\n");
		out.println("Repeat password锛�<input type=\"password\" name=\"password2\" /> <br />\r\n");
		out.println("<br />\r\n");
		// Output message
		if (request.getAttribute("message") != null) {
			out.print("<font color=\"red\"> ");
		    out.print(request.getAttribute("message"));
		    out.print("</font> <br />\r\n");
		    out.println("<br />\r\n");
		}
		out.println("<input type=\"submit\" value=\"Submit\" /> <br />\r\n");
		out.println("</form>\r\n");
		out.println("</body>\r\n");
		out.println("</html>\r\n");
		// Clear and close the output stream
		out.flush();
		out.close();
	}
}


