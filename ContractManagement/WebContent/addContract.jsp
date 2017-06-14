<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="model.Contract"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>起草合同</title>
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<script type="text/javascript">
			function check(){
				var name = document.getElementById('name');
				var beginTime = document.getElementById('beginTime');
				var endTime = document.getElementById('endTime');
				var content = document.getElementById('content');
				if(name.value == ""){
					alert("合同名不能为空!");
					name.focus();
					return false;
				}
				if(beginTime.value == ""){
					alert("开始时间不能为空!");
					beginTime.focus();
					return false;
				}
				if(endTime.value == ""){
					alert("终止时间不能为空!");
					endTime.focus();
					return false;
				}
				if(content.value == ""){
					alert("合同内容不能为空!");
					content.focus();
					return false;
				}
			}
		</script>
	</head>

	<body>
		<div class="mtitle">
			起草合同
		</div>
		<br />
		<div style="font-size:18px;color:green;width:700px;text-align:center;">
			<%
				if (request.getAttribute("message") != null) {
			%>
			<%=request.getAttribute("message")%>
			<%
				}
			%>			
		</div>

		<form action="draft" method="post">
			<table class="update" style="width:700px;">
				<%
					// Get contract object
					Contract contract = new Contract();
					if (request.getAttribute("contract") != null) {
				 		contract = (Contract)request.getAttribute("contract");
				 	}
				%>
				<tr height="28">
					<td width="140px">合同名称:</td>
					<td><input type="text" id="name" name="name" value="<%=contract.getName()%>" /><font color="red">&nbsp;&nbsp;*</font>
					</td>
				</tr>

				<tr height="28">
					<td>客户:</td>
					<td><input type="text" name="customer" value="" />
					</td>
				</tr>
				<tr>
					<td>开始时间:</td>	
					<td><input type="text" id="beginTime" name="beginTime"/><font color="red">&nbsp;&nbsp;*时间格式:yyyy-mm-dd</font>
					</td>
				</tr>
				<tr>
					<td>终止时间:</td>	
					<td><input type="text" id="endTime" name="endTime"/><font color="red">&nbsp;&nbsp;*时间格式:yyyy-mm-dd</font>
					</td>
				</tr>
				<tr>
					<td>合同内容:</td>	
					<td><font color="red">&nbsp;&nbsp;*</font>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<textarea id="content" name="content" style="width:680px;height:300px;resize: none;"><%=contract.getContent()%></textarea>
					</td>
				</tr>
				<tr height="28">
					<td>附件:</td>
					<td><input type="file" /></td>
				</tr>
				<tr height="28">
					<td align="center" colspan="2">
						<input type="submit" value="提交" class="button" onclick="return check()">
						 &nbsp; &nbsp; &nbsp;
						<input type="reset" value="重置" class="button">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
