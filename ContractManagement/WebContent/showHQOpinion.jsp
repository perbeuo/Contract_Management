<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.CSignatureOpinion"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Display countersign opinion</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
	</head>

	<body>
		<div class="mtitle">
			会签意见
		</div>
		
		<br />
		<div class="listOpinion">
			<table>
				<tr>
					<th width="100">
						操作员
					</th>
					<th  width="600">
						意见
					</th>
				</tr>
				<%
					List<CSignatureOpinion> csOpinionList = (List<CSignatureOpinion>)request.getAttribute("csOpinionList");  
				  	for (CSignatureOpinion csOpinion : csOpinionList) {	
				%>
				<tr>
					<td>
						<%=csOpinion.getCsOperator()%>
					</td>
					<td>
						<%=csOpinion.getOpinion()%>
					</td>
				</tr>
				<%} %>
			</table>
		</div>
		<br />
	</body>
</html>