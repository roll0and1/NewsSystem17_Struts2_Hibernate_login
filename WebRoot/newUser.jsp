<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath(); String basePath =
request.getScheme() + "://" + request.getServerName() + ":" +
request.getServerPort() + path + "/"; %>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻系统 — 新用户</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- header start -->
	<div class="header" style="height:55px">
		<div class="toplinks">
			您好：<%=session.getAttribute("name")%>，欢迎使用新闻系统！ <span>【<a href="index.html">返回首页</a>】【<a
				href="logout.jsp">注销登录</a>】
			</span>
		</div>
		<h1>
			<a href="#" target="_top"><img src="images/logo.png" height="56"
				width="260" alt="新闻系统" /> </a>
		</h1>
	</div>
	<!-- header end -->

	<!-- main start -->
	<div class="content" style="width:98%;height:450px;">
		<p>欢迎使用新闻系统！</p>
	</div>
	<!-- main end -->

	<!-- footer start -->
	<div class="footer">
		Copyright&nbsp;&copy;&nbsp;软酷卓越实验室&nbsp; <a
			href="http://www.ruanko.com" title="软酷网" target="_blank"><strong>软酷网</strong>
		</a>&nbsp;版权所有
	</div>
	<!-- footer end -->
</body>
</html>