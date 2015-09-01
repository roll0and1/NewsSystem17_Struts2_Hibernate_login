<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.qiangge.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<title>新闻系统 - 预览新闻</title>
<link href="css/admin.css" rel="stylesheet" media="screen"
	type="text/css" />
</head>

<body>
	<!-- newsInfor start -->
	<c:if test="${!empty news }">
		<div class="viewbox">
			<div class="title">
				<h2>${news.title }</h2>
			</div>
			<!-- /title -->
			<div class="info">
				<small>时间:</small>${news.createTime }<small>来源:</small>${news.source }
				<small>作者:</small>${news.author } <small>点击:</small>${news.click }
			</div>
			<!-- /info -->
			<div class="vContent">${news.content }</div>
		</div>
	</c:if>
	<!-- newsInfor end -->

	<!-- footer start -->
	<div class="footer">
		Copyright&nbsp;&copy;&nbsp;软酷卓越实验室&nbsp; <a
			href="http://www.ruanko.com" title="软酷网" target="_blank"><strong>软酷网</strong>
		</a>&nbsp;版权所有
	</div>
	<!-- footer end -->
</body>
</html>
