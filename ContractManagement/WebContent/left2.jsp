<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>合同管理系统 - 操作者菜单</title>
		<link href="css/frame.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="menu">
			<dl>
				<dt>
					起草
				</dt>
				<dd>
					<a href="toDraft" target="main">起草合同</a>
				</dd>
				<dd>
					<a href="#">查询进度</a>
				</dd>
			</dl>
			<dl>
				<dt>
					会签
				</dt>
				<dd>
					<a href="toDhqhtList" target="main">会签合同</a>
				</dd>
				<dd>
					<a href="#">已会签合同 </a>
				</dd>
			</dl>
			<dl>
				<dt>
					定稿
				</dt>
				<dd>
					<a href="toDdghtList" target="main">定稿合同</a>
				</dd>
				<dd>
					<a href="toYdghtList" target="main">已定稿合同</a>
				</dd>
			</dl>
			<dl>
				<dt>
					审批
				</dt>
				<dd>
					<a href="toDshphtList" target="main">审批合同</a>
				</dd>
				<dd>
					<a href="#">已审批合同</a>
				</dd>
			</dl>
			<dl>
				<dt>
					签订
				</dt>
				<dd>
					<a href="#">签订合同</a>
				</dd>
				<dd>
					<a href="#">已签订合同</a>
				</dd>
			</dl>
		</div>
	</body>
</html>
