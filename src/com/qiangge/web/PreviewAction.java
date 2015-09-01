package com.qiangge.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.News;
import com.qiangge.service.NewsService;
import com.qiangge.utils.AppException;

public class PreviewAction extends ActionSupport {
	private int id;
	private NewsService newsService = new NewsService();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String execute() {
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
}
