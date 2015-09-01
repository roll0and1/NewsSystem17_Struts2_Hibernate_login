<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新闻系统 - 编辑菜单栏</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/admin.css">

</head>

<body>
	<div class="menu">
		<ul class="c1 ico3">
			<li><a href="toCreateNews" target="main">创建新闻</a></li>
			<li><a href="myNews" target="main">我的新闻</a></li>
		</ul>
	</div>
</body>
</html>
