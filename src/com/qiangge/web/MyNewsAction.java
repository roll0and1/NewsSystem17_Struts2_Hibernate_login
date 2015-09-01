package com.qiangge.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.PageModel;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class MyNewsAction extends ActionSupport {
	private int state = 0; // 默认新闻状态
	private PageModel pageModel;
	private int currentPage = 1;
	private int size = 5;

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

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
			pageModel = newsService.getList(state, userId, currentPage, size);
			// // 将newsList存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("pageModel", pageModel);
			return "myNews";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 跳转到错误页面
			return "error";
		}

	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
}
