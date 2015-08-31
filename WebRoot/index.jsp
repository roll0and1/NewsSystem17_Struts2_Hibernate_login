<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.qiangge.model.*"%>

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

<title>首页--新闻列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="css/admin.css">

</head>

<body>
	<!--header start -->
	<jsp:include page="header2.jsp"></jsp:include>
	<!--header end  -->
	<div class="main">
		<%
			//获取新闻列表
			List<News> newsList = (List<News>) request.getAttribute("newsList");
			if (newsList != null) {
				for (News news : newsList) {
		%>
		<ul
			style="border-bottom:1px solid #aaa;font-size: 15px;margin: 12px 10px;padding: 7px 0px">
			<li><a href="#" id="<%=news.getId()%>" class="title"><%=news.getTitle()%></a></li>
		</ul>
	</div>
	<%
		}
		}
	%>
	<!--header start -->
	<jsp:include page="footer2.jsp"></jsp:include>
	<!--header end  -->
</body>
</html>
