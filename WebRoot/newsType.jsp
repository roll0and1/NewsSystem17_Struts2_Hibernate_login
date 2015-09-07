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
			<a href="index.htm"><img src="images/logo.png" width="260"
				height="56" alt="新闻系统" /> </a>
		</h1>
	</div>
	<!-- header end -->

	<!-- menu start -->
	<div class="menu">
		<ul>
			<li><a href="index.htm"><span>主页</span> </a></li>

			<li><a href="newsType.htm"><span>国际新闻</span> </a></li>

			<li><a href="newsType.htm"><span>国内新闻</span> </a></li>

			<li><a href="newsType.htm"><span>娱乐新闻</span> </a></li>

			<li><a href="newsType.htm"><span>体育新闻</span> </a></li>

			<li><a href="newsType.htm"><span>财经频道</span> </a></li>

			<li><a href="newsType.htm"><span>汽车频道</span> </a></li>

			<li><a href="newsType.htm"><span>电子频道</span> </a></li>
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
					<c:forEach var="news" items="${typeNewsPageModel.newsList }">
						<li><a href="news!detail?id=${news.id }" class="title">${news.title }</a>
							<small>日期：</small>${news.createTime } <small>点击：</small>${news.click }</li>

					</c:forEach>
				</c:if>

			</ul>
			<!-- newslist end -->

			<ul class="pagelist">
				<li><a
					href="news!getTypeNews?state=${state}&currentPage=<%=pageModel.getFirstPage()%>&size=${
				pageModel.size }">首页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&currentPage=<%=pageModel.getPrePage()%>&size=${
				pageModel.size }">上一页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&currentPage=<%=pageModel.getNextPage()%>&size=${
				pageModel.size }">下一页</a></li>
				<li><a
					href="news!getTypeNews?state=${state}&currentPage=<%=pageModel.getLastPage()%>&size=${
				pageModel.size }">末页</a></li>
				<li><span class="pageinfo"> 现在是第
						${pageModel.currentPage}页 , 共<%=pageModel.getTotalpages()%> 页
						共${pageModel.totalCount}条
				</span></li>
			</ul>
			<!-- pagelist end -->

		</div>
		<!-- left end -->

		<!-- right start -->
		<div class="right">
			<dl class="tbox">
				<dt>
					<strong>最新更新</strong>
				</dt>
				<dd>
					<ul class="ico1">
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>

					</ul>
				</dd>
			</dl>

			<dl class="tbox">
				<dt>
					<strong>热点内容</strong>
				</dt>
				<dd>
					<ul class="ico2">
						<li><a href="#">教师节，那些代课老师们</a></li>
						<li><a href="#">十堰他日必大兴</a></li>
						<li><a href="#">城市千金，我要远离农村婆婆</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">菲律宾总统再次将香港旅客被劫杀惨剧当笑料</a></li>
						<li><a href="#">十堰他日必大兴</a></li>
						<li><a href="#">十堰他日必大兴</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
						<li><a href="#">你所不知道的白岩松</a></li>
					</ul>
				</dd>
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