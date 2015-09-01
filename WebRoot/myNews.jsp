<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.qiangge.model.*"%>
<!-- 引入JSTL核心标签库 -->
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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

<title>新闻系统 - 我的新闻页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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

<body>
	<table>
		<tr bgcolor="#E7E7E7">
			<td height="24" colspan="4">&nbsp;我的新闻&nbsp;</td>
			<td>
				<!--表单  -->
				<form action="myNews" method="post">
					状态: <select name="state">
						<!--使用c:if标签判断用户选择，并显示其中的项  -->
						<option value="0" <c:if test="${state==0}">selected</c:if>>待审核</option>
						<option value="1" <c:if test="${state==1}">selected</c:if>>已发布</option>
					</select> <input type="submit" value="查询" />
				</form>
			</td>
		</tr>

		<!-- 新闻列表 -->
		<tr align="center" bgcolor="#F4F8FB" height="22">
			<td width="6%">序号</td>
			<td width="30%">文章标题</td>
			<td width="20%">录入时间</td>
			<td width="8%">栏目</td>
			<td width="18%">来源</td>
		</tr>

		<!--新闻信息列表  -->
		<c:if test="${!empty pageModel }">
			<c:forEach items="${pageModel.newsList}" var="news"
				varStatus="status">
				<tr align='center' height="22">
					<!--输出新闻信息  -->

					<!-- 输出序号 -->
					<td>${status.index+1}</td>
					<!--输出标题  -->
					<td align="left"><a href="javascript:void(0)">${news.title }</a></td>
					<!--输出创建时间  -->
					<td>${news.createTime}</td>
					<!--输出新闻栏目  -->
					<td>${news.newsType}</td>
					<!--输出新闻来源  -->
					<td>${news.source}</td>
			</c:forEach>

		</c:if>

		<!--空白行  -->
		<tr>

			<td height="24" colspan="5">&nbsp;</td>
		</tr>
		<!--分页  -->
		<tr bgcolor="#F4F8FB">
			<%
				PageModel pageModel = (PageModel) request.getAttribute("pageModel");
				if (pageModel != null) {
			%>
			<td colspan="9" align="right" style="padding:0 52px;">现在是第
				${pageModel.currentPage}页 , 共<%=pageModel.getTotalpages()%> 页
				共${pageModel.totalCount}条记录&nbsp;&nbsp; <a
				href="myNews?state=${state}&currentPage=<%=pageModel.getFirstPage()%>&size=${
				pageMode.size }">首页</a>
				<a href="myNews?state=${state}&currentPage=<%=pageModel.getPrePage()%>&size=${
				pageMode.size }">上一页</a> <a
				href="myNews?state=${state}&currentPage=<%=pageModel.getNextPage()%>&size=${
				pageMode.size }">下一页</a> <a
				href="myNews?state=${state}&currentPage=<%=pageModel.getLastPage()%>&size=${
				pageMode.size }">末页</a>
			</td>
		</tr>


		<%
			}
		%>
	</table>

</body>
</html>
