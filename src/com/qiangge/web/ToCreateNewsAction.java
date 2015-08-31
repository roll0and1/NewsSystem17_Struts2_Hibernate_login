package com.qiangge.web;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;

public class ToCreateNewsAction extends HttpServlet {

	public String execute() {
		// 获取session
		Map<String, Object> session = ActionContext.getContext().getSession();
		String name = (String) session.get("name");
		if (null == name) {
			return "login";
		} else {
			return "create";
		}
	}

}
