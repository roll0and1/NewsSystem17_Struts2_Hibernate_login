package com.qiangge.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.News;
import com.qiangge.model.PageModel;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class NewsAction extends ActionSupport {
	private News news;
	private int id;

	NewsService newsService = new NewsService();

	private int state = 0; // 默认新闻状态

	private PageModel pageModel;

	private int currentPage = 1;
	private int size = 5;

	public String create() {
		String message = null;
		HttpServletRequest request = null;
		// 获取userId
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = (int) session.get("userId");
		String name = (String) session.get("name");
		// 验证用户是否登录
		if (null == name) {
			return "login";
		}

		news.setUserId(userId);

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public News getNews() {
		return news;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public int getSize() {
		return size;
	}

	public int getState() {
		return state;
	}

	public String myNews() {
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

	public String preview() {
		// 判断是是否登录
		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer userId = (Integer) session.get("userId");
		if (null == userId) {
			return "login";
		}

		News news = null;
		try {
			news = newsService.preview(id);
			// 将news存入request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			request.put("news", news);
			return "preview";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public String toCreate() {
		// 获取session
		Map<String, Object> session = ActionContext.getContext().getSession();
		String name = (String) session.get("name");
		if (null == name) {
			return "login";
		} else {
			return "create";
		}
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setState(int state) {
		this.state = state;
	}

}
