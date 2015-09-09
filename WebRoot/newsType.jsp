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



</head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻系统 - 栏目新闻列表</title>
<meta name="description" content="软酷新闻发布系统" />
<meta name="keywords" content="软酷网 新闻发布" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="toplinks">
			<span>【<a href="register.jsp">注册</a>】【<a href="login.jsp">登录</a>】
			</span>
		</div>

		<h1>
			<a href="news!index"><img src="images/logo.png" width="260"
				height="56" alt="新闻系统" /> </a>
		</h1>
	</div>
	<!-- header end -->

	<!-- menu start -->
	<div class="menu">
		<ul>
			<li><a href="news!index"><span>主页</span> </a></li>

			<li><a
				href="news!getTypeNews?state=${state }&newsTypeId=1&currentPage=${currentPage}&size=${size }"><span>国际新闻</span>
			</a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=2&currentPage=${currentPage}&size=${size }"><span>国内新闻</span> </a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=3&currentPage=${currentPage}&size=${size }"><span>娱乐新闻</span> </a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=4&currentPage=${currentPage}&size=${size }"><span>体育新闻</span> </a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=5&currentPage=${currentPage}&size=${size }"><span>财经频道</span> </a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=6&currentPage=${currentPage}&size=${size }"><span>汽车频道</span> </a></li>

			<li><a href="news!getTypeNews?state=${state }&newsTypeId=7&currentPage=${currentPage}&size=${size }"><span>电子频道</span> </a></li>
		</ul>

		<form action="" method="post">
			<input type="hidden" name="newstypeId" value="0" /> <input
				name="name" type="text" class="search-keyword" id="search-keyword"
				value="在这里搜索..." /> <input type="submit" class="search-submit"
				value="搜索" />
		</form>
	</div>
	<!-- menu end -->

	<!-- main start -->
	<div class="main">
		<!-- left start -->
		<div class="left">
			<div class="place">
				<strong>当前位置:</strong> 主页 &gt; 文章列表
			</div>
			<!-- place end -->
			<ul class="newslist">
				<c:if test="${!empty typeNewsPageModel }">
					<c:forEach var="news" items="${typeNewsPageModel.typeNewList }">
						<li><a href="news!detail?id=${news.id }" class="title">${news.title }</a>
							<small>日期：</small>${news.createTime } <small>点击：</small>${news.click }</li>

					</c:forEach>
				</c:if>

			</ul>
			<!-- newslist end -->

			<ul class="pagelist">
				<%
					PageModel typeNewsPageModel = (PageModel) request
							.getAttribute("typeNewsPageModel");
					if (typeNewsPageModel != null) {
				%>
				<li><a
					href="news!getTypeNews?state=${state}&newsTypeId=${ newsTypeId}&currentPage=<%=typeNewsPageModel.getFirstPage()%>&size=${
				typeNewsPageModel.size }">首页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&newsTypeId=${ newsTypeId}&currentPage=<%=typeNewsPageModel.getPrePage()%>&size=${
				typeNewsPageModel.size }">上一页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&newsTypeId=${ newsTypeId}&currentPage=<%=typeNewsPageModel.getNextPage()%>&size=${
				typeNewsPageModel.size }">下一页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&newsTypeId=${ newsTypeId}&currentPage=<%=typeNewsPageModel.getLastPage()%>&size=${
				typeNewsPageModel.size }">末页</a></li>
				<li><span class="pageinfo"> 现在是第
						${typeNewsPageModel.currentPage}页 , 共<%=typeNewsPageModel.getTotalpages()%>
						页 共${typeNewsPageModel.totalCount}条
				</span></li>
			</ul>

			<%
				}
			%>
			<!-- pagelist end -->

		</div>
		<!-- left end -->

		<!-- right start -->
		<div class="right">
			<dl class="tbox">
				<dt>
					<strong>最新更新</strong>
				</dt>
				<c:if test="${!empty latestNewsList }">
					<c:forEach items="${latestNewsList }" var="news">
						<dd>
							<ul class="ico3">

								<li><a href="news!detail?id=${news.id }">${news.title }</a></li>

							</ul>

						</dd>
					</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong>热点内容</strong>
				</dt>
				<c:if test="${!empty hotNewsList }">
					<c:forEach items="${hotNewsList }" var="news">
						<dd>
							<ul class="ico3">

								<li><a href="news!detail?id=${news.id }">${news.title }</a></li>

							</ul>

						</dd>
					</c:forEach>
				</c:if>
			</dl>
		</div>
		<!-- right end -->
	</div>
	<!-- main end -->

	<!-- footer start -->
	<div class="footer">
		<p>
			Copyright&nbsp;&copy;&nbsp;软酷卓越实验室&nbsp;<a
				href="http://www.ruanko.com" title="软酷网" target="_blank"><strong>软酷网</strong></a>&nbsp;版权所有
		</p>
	</div>
	<!-- footer end -->
</body>
</html>