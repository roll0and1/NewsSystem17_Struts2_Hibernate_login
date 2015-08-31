package com.qiangge.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {

	public String execute() {
		// 移除session中的name和userId
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("name");
		session.remove("userId");
		return "logout";
	}

}
