/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.44
 * Generated at: 2017-06-12 12:57:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import model.User;
import model.Contract;

public final class assignOperator_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.Contract");
    _jspx_imports_classes.add("model.User");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<title>Assign operator</title>\r\n");
      out.write("\t\t<link href=\"css/style.css\" rel=\"stylesheet\" media=\"screen\"\r\n");
      out.write("\t\t\ttype=\"text/css\" />\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tfunction moveOption(s1,s2){\r\n");
      out.write("\t\t\t\t// Cteate cache array to store value and text\r\n");
      out.write("\t\t\t\tvar arrSelValue = new Array();  \r\n");
      out.write("\t\t\t\tvar arrSelText = new Array();  \r\n");
      out.write("\t\t\t\t// This array stores the selected options, corresponding to value\r\n");
      out.write("\t\t\t\tvar arrValueTextRelation = new Array();  \r\n");
      out.write("\t\t\t\tvar index = 0;// Assist to establish the cache array\r\n");
      out.write("\t\t\t\t  \r\n");
      out.write("\t\t\t\t// Store all data in the source list to the cache, and establish the corresponding relationship of value and selected option\r\n");
      out.write("\t\t\t   for ( var i = 0; i < s1.options.length; i++) {  \r\n");
      out.write("\t\t\t\t\tif (s1.options[i].selected) {  \r\n");
      out.write("\t\t\t\t\t\tarrSelValue[index] = s1.options[i].value;  \r\n");
      out.write("\t\t\t\t\t\tarrSelText[index] = s1.options[i].text;  \r\n");
      out.write("\t\t\t\t\t\t// Cteate the corresponding relationship of value and selected option\r\n");
      out.write("\t\t\t\t\t\tarrValueTextRelation[arrSelValue[index]] = s1.options[i];  \r\n");
      out.write("\t\t\t\t\t\tindex++;  \r\n");
      out.write("\t\t\t\t\t}  \r\n");
      out.write("\t\t\t\t}  \r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t\t\t// Increase cache data to purpose list, and delete the corresponding item in source list\r\n");
      out.write("\t\t\t\tfor ( var i = 0; i < arrSelText.length; i++) {  \r\n");
      out.write("\t\t\t\t\tvar oOption = document.createElement(\"option\");  \r\n");
      out.write("\t\t\t\t\toOption.text = arrSelText[i];  \r\n");
      out.write("\t\t\t\t\toOption.value = arrSelValue[i];  \r\n");
      out.write("\t\t\t\t\ts2.add(oOption);\r\n");
      out.write("\t\t\t\t\ts2.options[i].selected=true;  \r\n");
      out.write("\t\t\t\t\t// Delete the corresponding item in source list\r\n");
      out.write("\t\t\t\t\ts1.removeChild(arrValueTextRelation[arrSelValue[i]]);  \r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t// Judgment whether user has assigned operator, if does not assign, giving prompt message; Or submit form to assign operator\r\n");
      out.write("\t\t\tfunction check(){\r\n");
      out.write("\t\t\t\t// Cteate cache array to store assigned operator\r\n");
      out.write("      \t\t   \tvar assignedOperator = new Array(3); \r\n");
      out.write("\t\t\t\tassignedOperator[0] = document.assignOperForm.hqht;\r\n");
      out.write("\t\t\t\tassignedOperator[1] = document.assignOperForm.spht;\r\n");
      out.write("\t\t\t\tassignedOperator[2] = document.assignOperForm.qdht;\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// If there is no assigned operator, giving a prompt message\r\n");
      out.write("\t\t\t\tif((assignedOperator[0].length) < 1){\r\n");
      out.write("\t\t\t\t\talert(\"Please assign countersign operator!\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif((assignedOperator[1].length) < 1){\r\n");
      out.write("\t\t\t\t\talert(\"Please assign approver!\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif((assignedOperator[2].length) < 1){\r\n");
      out.write("\t\t\t\t\talert(\"Please assign signer!\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t} \r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t");

			Contract contract = (Contract)request.getAttribute("contract");
		
      out.write("\r\n");
      out.write("\t\t<div class=\"mtitle\">\r\n");
      out.write("\t\t\tAssign operator: ");
      out.print(contract.getName());
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t");

			List<User> userList = (List<User>)request.getAttribute("userList");
        
      out.write("\r\n");
      out.write("\t\t<br /><br />\r\n");
      out.write("\t\t<form name=\"assignOperForm\" action=\"assignOper\" method=\"post\">\r\n");
      out.write("\t\t\t<input type=\"hidden\" name=\"conId\" value=\"");
      out.print(contract.getId());
      out.write("\">\r\n");
      out.write("\t\t\t<h3>\r\n");
      out.write("\t\t\t\t<img src=\"images/cog_edit.png\"  alt=\"Assign countersign people\" />\r\n");
      out.write("\t\t\t\tAssign countersign operator\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<table border=\"0\" width=\"200\" class=\"update\"> \r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\toperator to be assigned: \r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"dfphqht\" size=\"12\">\r\n");
      out.write("\t\t\t\t\t\t    ");
  
						    	for (User user : userList) {
			       	 		
      out.write(" \r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"");
      out.print(user.getId());
      out.write('"');
      out.write('>');
      out.print(user.getName());
      out.write("</option> \r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"10%\" align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&gt&gt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.dfphqht, document.assignOperForm.hqht)\">\r\n");
      out.write("\t\t\t\t\t\t<br/> <br/> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&lt&lt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.hqht, document.assignOperForm.dfphqht)\"> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\tassigned operator:\r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"hqht\" size=\"12\"> \r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t</tr> \t\t\t\t\r\n");
      out.write("\t\t\t</table> \r\n");
      out.write("\t\t\t<br />\r\n");
      out.write("\t\t\t<h3>\r\n");
      out.write("\t\t\t\t<img src=\"images/cog_edit.png\"  alt=\"Assign approver\" />\r\n");
      out.write("\t\t\t\tAssign approver\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<table border=\"0\" width=\"400\"  class=\"update\"> \r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\toperator to be assigned: \r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"dfpspht\" size=\"12\"> \r\n");
      out.write("\t\t\t\t\t\t\t ");
  
						    	for (User user : userList) {
			       	 		
      out.write("  \r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"");
      out.print(user.getId());
      out.write('"');
      out.write('>');
      out.print(user.getName());
      out.write("</option> \r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"10%\" align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&gt&gt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.dfpspht, document.assignOperForm.spht)\">\r\n");
      out.write("\t\t\t\t\t\t<br/> <br/> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&lt&lt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.spht, document.assignOperForm.dfpspht)\"> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\tassigned operator:\r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"spht\" size=\"12\"> \r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t</tr> \t\t\t\t\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<br />\r\n");
      out.write("\t\t\t<h3>\r\n");
      out.write("\t\t\t\t<img src=\"images/cog_edit.png\"  alt=\"Assign signer\" />\r\n");
      out.write("\t\t\t\tAssign signer\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<table border=\"2\" width=\"400\"  class=\"update\"> \r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\toperator to be assigned: \r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"dfpqdht\" size=\"12\"> \r\n");
      out.write("\t\t\t\t\t\t\t ");
  
						    	for (User user : userList) {
			       	 		
      out.write(" \r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"");
      out.print(user.getId());
      out.write('"');
      out.write('>');
      out.print(user.getName());
      out.write("</option> \r\n");
      out.write("\t\t\t\t\t\t\t");
} 
      out.write(" \r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"10%\" align=\"center\"> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&gt&gt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.dfpqdht, document.assignOperForm.qdht)\">\r\n");
      out.write("\t\t\t\t\t\t<br/> <br/> \r\n");
      out.write("\t\t\t\t\t\t<input type=\"button\" value=\"&lt&lt\" \r\n");
      out.write("\t\t\t\t\tonclick=\"moveOption(document.assignOperForm.qdht, document.assignOperForm.dfpqdht)\"> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t\t<td width=\"45%\"> \r\n");
      out.write("\t\t\t\t\t\tassigned operator:\r\n");
      out.write("\t\t\t\t\t\t<select style=\"WIDTH:100%\" multiple name=\"qdht\" size=\"12\"> \r\n");
      out.write("\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t</td> \r\n");
      out.write("\t\t\t\t</tr> \t\t\t\t\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table width=\"400\" class=\"update\"> \r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\" style=\"text-align:center;\">\r\n");
      out.write("\t\t\t\t<input type=\"submit\" value=\"Submit\" class=\"button\" onclick=\"return check()\"> &nbsp; &nbsp; &nbsp; \r\n");
      out.write("\t\t\t\t<input type=\"reset\" value=\"Reset\" class=\"button\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t\t</form> \r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
