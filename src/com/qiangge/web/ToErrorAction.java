package com.qiangge.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ToErrorAction extends ActionSupport {

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer userId = (Integer) session.get("userId");
		String name = (String) session.get("name");
		// 验证用户是否登录
		if (null == userId) {
			return "login";
		}
		return "error";
	}
}
