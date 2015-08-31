package com.qiangge.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.News;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class CreateNewsAction extends ActionSupport {

	private News news;

	NewsService newsService = new NewsService();

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String execute() {
		
		
		
		
		String message = null;
		HttpServletRequest request = null;
		// 获取userId
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = (int) session.get("userId");
		String name = (String) session.get("name");
		// 验证用户是否登录
		if(null==name){
			return "login";
		}
		
		news.setUserId(userId);

		// news.setAuthor(author);
		// news.setContent(content);
		// news.setCreateTime(createTime);
		// news.setKeywords(keywords);
		// news.setNewsTypeId(newsTypeId);
		// news.setTitle(title);
		// news.setSource(source);
		news.setState(0); // 设置状态为"未审核"

		try {
			boolean flag = newsService.create(news);
			if (flag) {
				message = "创建成功";
			} else {
				message = "创建失败";
			}
			// 将message和news对象存入到request中
			// 获取request
			request = (HttpServletRequest) ActionContext.getContext().get(
					ServletActionContext.HTTP_REQUEST);
			// 将message和news对象存入request
			request.setAttribute("message", message);
			request.setAttribute("news", news);
			return "create";

		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 重定向到错误页面
			return "error";
		}

	}
}
