<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.qiangge.model.*"%>
<!-- 引入JSTL核心标签库 -->
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
<title>新闻系统 - 审核新闻页面</title>
<link href="css/admin.css" rel="stylesheet" media="screen"
	type="text/css" />
<!-- 预览新闻时，使用JavaScript脚本打开新窗口显示信息 -->
<script>
	function preview(url) {
		window.open(url, '预览',
				'resizable=no,toolbar=no,width=620,height=500,top=50,left=200');
	}
</script>
</head>
</head>

<body>
	<table>
		<tr bgcolor="#E7E7E7">
			<td height="24" colspan="6">&nbsp;审核新闻&nbsp;</td>
		</tr>
		<tr align="center" bgcolor="#F4F8FB" height="22">
			<td width="6%">序号</td>
			<td width="24%">文章标题</td>
			<td width="18%">录入时间</td>
			<td width="8%">栏目</td>
			<td width="6%">录入者</td>
			<td width="12%">操作</td>
		</tr>
		<c:if test="${!empty newsPageModel }">

			<c:forEach varStatus="status" items="${newsPageModel.newsList }"
				var="newsIn">
				<!--items:要遍历的对象 val：遍历对象的元素   status：元素的状态 -->

				<tr align='center' height="22">
					<td>${status.index+1}</td>
					<td align="left"><a
						href="javascript:preview('news!preview?id=${newsIn.id }')">${newsIn.title }</a></td>
					<td>${newsIn.createTime }</td>
					<td>${newsIn.name }</td>
					<td>${newsIn.creator }</td>
					<td><a href="news!check?id=${newsIn.id }&checkNewsState=1"
						target="main">通过</a> | <a
						href="news!check?id=${newsIn.id }&checkNewsState=2" target="main">不通过</a></td>
				</tr>
			</c:forEach>

		</c:if>

		<!--空白行  -->
		<tr>
			<td height="24" colspan="6">&nbsp;</td>
		</tr>
		<!--分页  -->
		<tr bgcolor="#F4F8FB">
			<%
				PageModel pageModel = (PageModel) request
						.getAttribute("newsPageModel");
				if (pageModel != null) {
			%>
			<td colspan="9" align="right" style="padding:0 52px;">现在是第
				${pageModel.currentPage}页 , 共<%=pageModel.getTotalpages()%> 页
				共${pageModel.totalCount}条记录&nbsp;&nbsp; <a
				href="news!toUncheck?checkNewsState=${checkNewsState}&checkNewsCurrentPage=<%=pageModel.getFirstPage()%>&size=${
				pageModel.size }">首页</a>
				<a
				href="news!toUncheck?checkNewsState=${checkNewsState}&checkNewsCurrentPage=<%=pageModel.getPrePage()%>&size=${
				pageModel.size }">上一页</a>
				<a
				href="news!toUncheck?checkNewsState=${checkNewsState}&checkNewsCurrentPage=<%=pageModel.getNextPage()%>&size=${
				pageModel.size }">下一页</a>
				<a
				href="news!toUncheck?checkNewsState=${checkNewsState}&checkNewsCurrentPage=<%=pageModel.getLastPage()%>&size=${
				pageModel.size }">末页</a>
			</td>
		</tr>


		<%
			}
		%>

	</table>
</body>
</html>