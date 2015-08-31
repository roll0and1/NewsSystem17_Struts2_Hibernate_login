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

<title>新闻系统 - 页脚</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/admin.css">

</head>

<body>
	<div class="footer">
		<p>
			Copyright&nbsp;&copy;&nbsp;软酷卓越实验室&nbsp;<a
				href="http://www.ruanko.com" title="软酷网" target="_blank"><strong>软酷网</strong></a>&nbsp;版权所有
		</p>
	</div>
</body>
</html>
