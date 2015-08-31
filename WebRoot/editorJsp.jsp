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

<title>新闻系统 - 管理员管理页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>

<!-- 框架结构：上、中(左边、右边)、下 -->
<frameset rows="72,*,70" cols="*" frameborder="no">
	<frame src="header.jsp" name="topframe" scrolling="no">
	<frameset cols="180,*" frameborder="no">
		<frame src="editorMenu.jsp" noresize name="menu" scrolling="no">
		<frame src="welcome.jsp" noresize name="main" scrolling="auto">
	</frameset>
	<frame src="footer.jsp" name="bottomframe" scrolling="no">
</frameset>
<noframes>
	<body>您的浏览器不支持框架！
	</body>
</noframes>
</html>
