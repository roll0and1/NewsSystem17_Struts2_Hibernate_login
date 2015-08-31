package com.qiangge.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qiangge.model.News;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class CreateNews extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		News news = new News();
		NewsService newsService = new NewsService();
		String message = null;

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		String source = (String) request.getParameter("source");
		String author = (String) request.getParameter("author");
		String createTime = (String) request.getParameter("createTime");
		String keywords = (String) request.getParameter("keywords");
		String title = (String) request.getParameter("title");
		int newsTypeId = Integer.parseInt((String) request.getParameter("newsTypeId")
				);
		String content = (String) request.getParameter("content");

		news.setUserId(userId);
		news.setAuthor(author);
		news.setContent(content);
		news.setCreateTime(createTime);
		news.setKeywords(keywords);
		news.setNewsTypeId(newsTypeId);
		news.setTitle(title);
		news.setSource(source);
		news.setState(0); // 设置状态为"未审核"

		try {
			boolean flag = newsService.create(news);
			if (flag) {
				message = "创建成功";
			} else {
				message = "创建失败";
			}
			// 将message和news对象存入到request中
			request.setAttribute("message", message);
			request.setAttribute("news", news);
			// 转发到createNews页面
			request.getRequestDispatcher("createNews.jsp").forward(request, response);
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			 重定向到错误页面
			response.sendRedirect("error.jsp");
		}

	}
}
