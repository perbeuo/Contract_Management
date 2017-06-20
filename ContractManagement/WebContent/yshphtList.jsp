<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.ConBusiModel"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="css/style.css" rel="stylesheet" media="screen"
			type="text/css" />
		<title>已审批合同列表</title>
		<!-- Use JavaScript script to open a new window display information when preview-->
		<script>
			function preview(url) {
				window.open(url,'Preview','resizable=no,toolbar=no,width=620,height=500,top=50,left=200');
			}
		</script>
	</head>

	<body>
		<div class="mtitle">
			已审批合同
		</div>
		
		<div class="search">
			<form>
				查找已审批合同
				<input value="" />
				&nbsp;&nbsp;
				<input type="submit" value="Search" class="search-submit"/> <br />
			</form>
		</div>
		
		<div class="list">
		  <table>
			<tr>
				<th>
					合同名
				</th>
				<th class="th1">
					审批时间
				</th>
				<th width="270px">
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
					<%=cbm.getFinalTime()%>
				</td>
				<td>
					<a href="showGOODSHPOpinion?conId=<%=cbm.getConId()%>">
						<img src="images/information.png"  alt="Approve opinion" />
						审批意见
					</a> 
					&nbsp;
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
