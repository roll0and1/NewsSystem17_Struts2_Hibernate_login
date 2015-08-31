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

<title>新闻系统 - 欢迎</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="css/admin.css">
<script type="text/javascript">
	function add0(a) {
		if (a >= 0 && a < 10) {
			a = "0"+a;
		}
		return a;
	}
	function showTime() {
		var date;
		var hour;
		var minute;
		var second;
		date = new Date();
		hour = date.getHours();
		minute = date.getMinutes();
		second = date.getSeconds();
		document.getElementById("time").innerHTML=add0(hour)+":"+add0(minute)+":"+add0(second);
	}
	window.setInterval("showTime();", 1000);
</script>

</head>

<body>
	<div class="content">
		<p>
			欢迎进入新闻系统！ <br /> 现在时间：<span id="time"></span>

		</p>
	</div>
</body>
</html>
