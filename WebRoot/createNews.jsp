<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.qiangge.model.News"%>
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

<title>新闻系统 - 创建新闻页面</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
内部样式表-->tr {
	height: 22px;
}

.td {
	text-align: center;
}
</style>
<script type="text/javascript">
	function check() {
		var title = document.getElementById("title").value;
		var newsTypeId = document.getElementById("newsTypeId").value;
		var content = document.getElementById("content").value;

		if (title == "") {
			alert("请填写标题！");
			// 返回false，阻止表单提交
			return false;
		}

		if (newsTypeId == "0") {
			alert("请选择新闻栏目！");
			return false;
		}

		if (content == "") {
			alert("请填写内容！");
			return false;
		}
	}
</script>
</head>

<body>
	<!--  新闻表单   -->
	<%
		News news = new News();
		if (request.getAttribute("news") != null) {
			news = (News) request.getAttribute("news");
		}
	%>
	<form id="createNewsform" name="createNewsform" method="post"
		action="news!create">
		<table>
			<tr bgcolor="#E7E7E7">
				<td height="24" colspan="10">&nbsp;创建新闻&nbsp;</td>
			</tr>
			<tr>
				<td class="td">标题：</td>
				<td><input type="text" name="news.title" id="title" size="60"
					value="${news.title } " /></td>
			</tr>
			<tr>
				<td class="td">栏目：</td>
				<%
					int newsTypeId = 0;
					newsTypeId = news.getNewsTypeId();
				%>
				<td><select id="newsTypeId" name="news.newsTypeId">
						<option value="0">请选择栏目</option>
						<option value="1" <%if (newsTypeId == 1) {%> selected <%}%>>国际新闻</option>
						<option value="2" <%if (newsTypeId == 2) {%> selected <%}%>>国内新闻</option>
						<option value="3" <%if (newsTypeId == 3) {%> selected <%}%>>娱乐新闻</option>
						<option value="4" <%if (newsTypeId == 4) {%> selected <%}%>>体育新闻</option>
						<option value="5" <%if (newsTypeId == 5) {%> selected <%}%>>财经频道</option>
						<option value="6" <%if (newsTypeId == 6) {%> selected <%}%>>汽车频道</option>
						<option value="7" <%if (newsTypeId == 7) {%> selected <%}%>>电子频道</option>
				</select></td>
			</tr>
			<tr>
				<td class="td">来源：</td>
				<td><input type="text" name="news.source" id="source"
					value="${news.source }" /></td>
			</tr>
			<tr>
				<td class="td">作者：</td>
				<td><input type="text" name="news.author" id="author"
					value="${news.author }" /></td>
			</tr>
			<tr>
				<td class="td">创建时间：</td>
				<%
					Date date = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				%>
				<td><input type="text" name="news.createTime" id="createTime"
					value="<%=df.format(date)%>" /></td>
			</tr>
			<tr>
				<td class="td">关键字：</td>
				<td><input type="text" name="news.keywords" id="keywords"
					size="60" value="${news.keywords}" /></td>
			</tr>
			<tr>
				<td class="td">内容：</td>
				<td><textarea id="content" name="news.content"
						style="width:600px; height:230px;">${news.content} </textarea></td>
			</tr>
			<tr>
				<td class="td">附件：</td>
				<td><input type="file" /></td>
			</tr>
			<%
				String message = (String) request.getAttribute("message");
				if (message != null) {
			%>
			<tr>
				<td align="center" id="message">${message}</td>
			</tr>

			<%
				}
			%>

			<tr align="center">
				<td colspan="2"><input type="submit" value="提交" class="button"
					onclick="return check()" /> <input type="reset" value="重置"
					class="button" /></td>
			</tr>
		</table>
	</form>
</body>
</html>