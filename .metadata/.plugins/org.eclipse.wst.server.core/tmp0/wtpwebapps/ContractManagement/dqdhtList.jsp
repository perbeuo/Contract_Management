<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.ConBusiModel"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<title>待签订合同列表</title>
		<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','toolbar=no,scrollbars=yes,width=720,height=560');
			}
		</script>
	</head>

	<body>
		<div class="mtitle">
			待签订合同
		</div>
		
		<div class="search">
			<form>
				查找待签订合同：
				<input value="" />
				&nbsp;&nbsp;
				<input type="submit" value="查询" class="search-submit"/> <br />
			</form>
		</div>
		
		<div class="list">
		  <table>
			<tr>
				<th>
					合同名
				</th>
				<th class="th1">
					起草时间
				</th>
				<th  class="th1">
					操作
				</th>
			</tr>
			<%
				List<ConBusiModel> contractList = (List<ConBusiModel>)request.getAttribute("contractList");  
		        for (ConBusiModel cbm : contractList) {
       	 	%>
			<tr>
				<td class="tdname">
					<a href="javascript:preview('contractDetail?conId=<%=cbm.getConId()%>')"><%=cbm.getConName()%></a>
				</td>
				<td>
					<%=cbm.getDrafTime()%>
				</td>
				<td>
					<a href="toAddQDInfo?conId=<%=cbm.getConId()%>">
						<img src="images/icon-edit.png"  alt="Sign" />
						签订
					</a>
				</td>
			</tr>
			<%} %>
			<tr>
				<td colspan="3"> </td>
			</tr>
		  </table>
		</div>

		<div align="right" class="pagelist">					
			<a href="#"><img src="images/page/first.png"  alt="" /></a> &nbsp;
			<a href="#"><img src="images/page/pre.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/next.png"  alt="" /></a>&nbsp;
			<a href="#"><img src="images/page/last.png"  alt="" /></a>&nbsp;
					
		</div>
	</body>
</html>
