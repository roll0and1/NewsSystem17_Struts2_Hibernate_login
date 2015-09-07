<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.qiangge.model.*"%>
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

<title>新闻系统 - 首页</title>
<meta name="description" content="软酷新闻发布系统" />
<meta name="keywords" content="软酷网 新闻发布" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
	function preview(url) {
		window.open(url, '预览',
				'resizable=no,toolbar=no,width=620,height=500,top=50,left=200');
	}
</script>

</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="toplinks">
			<span>【<a href="register.jsp">注册</a>】【<a href="login.jsp">登录</a>】
			</span>
		</div>

		<h1>
			<a href="index.html"><img src="images/logo.png" width="260"
				height="56" alt="新闻系统" /> </a>
		</h1>
	</div>
	<!-- header end -->

	<!-- menu start -->
	<div class="menu">
		<ul>
			<li><a href="news!index"><span>主页</span> </a></li>

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
			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">国际新闻</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty internationalNewsList }">
				<c:forEach items="${internationalNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">国内新闻</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty domesticNewsList }">
				<c:forEach items="${domesticNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">娱乐新闻</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty entertainmentNewsList }">
				<c:forEach items="${entertainmentNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">体育新闻</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty sportsNewsList }">
				<c:forEach items="${sportsNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">财经频道</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty financialNewsList }">
				<c:forEach items="${financialNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>

			<dl class="tbox">
				<dt>
					<strong><a href="newsType.htm">汽车频道</a> </strong><span class="more"><a
						href="newsType.htm">更多...</a> </span>
				</dt>
				<c:if test="${!empty autoNewsList }">
				<c:forEach items="${autoNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

					</ul>
					
				</dd>
				</c:forEach>
				</c:if>
			</dl>
		</div>
		<!-- left end -->

		<!-- right start -->
		<div class="right">
			<dl class="tbox">
				<dt>
					<strong>最新更新</strong>
				</dt>
				<c:if test="${!empty latestNewsList }">
				<c:forEach items="${latestNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

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
				<c:forEach items="${hotNewsList }" var="news" >
				<dd>
					<ul class="ico3">

						<li><a
						href="javascript:preview('news!preview?id=${news.id }')">${news.title }</a></li>

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
