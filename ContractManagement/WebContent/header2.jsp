<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Contract Management System - Head</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="header">
			<div class="toplinks">
				<%
					String userName = (String) session.getAttribute("userName");
				%>
				<span>您好:<%=userName%>，欢迎使用合同管理系统 [<a href="logout"
					target="_top">退出</a>]</span>
			</div>
			
		</div>
	</body>
</html>