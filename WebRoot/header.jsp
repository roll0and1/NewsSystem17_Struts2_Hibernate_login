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

<title>新闻系统 - 页头</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/admin.css">

</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="toplinks">
			您好：<%=session.getAttribute("name") %>，欢迎使用新闻系统！<span>【<a href="news!index" target="_top">返回首页</a>】【<a
				href="user!logout" target="_top">注销登录</a>】
			</span>
		</div>
		<h1>
			<a href="news!index" target="_top"><img src="images/logo.png"
				height="56" width="260" alt="新闻系统" /></a>
		</h1>
	</div>
	<!-- header end -->
</body>
</html>
