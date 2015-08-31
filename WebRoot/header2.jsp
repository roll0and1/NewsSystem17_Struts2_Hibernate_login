<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="header">
	<div class="toplinks">
		您好：<%=session.getAttribute("name")%>，欢迎使用新闻系统！<span>【<a
			href="index.html" target="_top">返回首页</a>】【<a href="logout"
			target="_top">注销登录</a>】
		</span>
	</div>
	<h1>
		<a href="index.htm" target="_top"><img src="images/logo.png"
			height="56" width="260" alt="新闻系统" /></a>
	</h1>
</div>
