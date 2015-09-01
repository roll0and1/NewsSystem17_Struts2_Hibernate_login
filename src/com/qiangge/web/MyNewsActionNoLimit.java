package com.qiangge.web;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.NewsModel;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class MyNewsActionNoLimit extends ActionSupport {
	private int state = 0; // 默认新闻状态
	private List<NewsModel> newsList;
	private NewsService newsService = new NewsService();

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String execute() {
		// 初始化session
		Map<String, Object> session = ActionContext.getContext().getSession();
		// 获取用户信息
		Integer userId = (Integer) session.get("userId");
		// 判断用户是否登录
		if (null == userId) {
			// 用户未登录，重定向到login
			return "login";
		}
		try {
			// 获取当前用户创建的新闻信息
			List<NewsModel> newsList = newsService.getList(state, userId);
//			// 将newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("newsList", newsList);
			return "myNews";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 跳转到错误页面
			return "error";
		}

	}

	public List<NewsModel> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<NewsModel> newsList) {
		this.newsList = newsList;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
}
