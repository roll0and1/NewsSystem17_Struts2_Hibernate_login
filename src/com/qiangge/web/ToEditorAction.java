package com.qiangge.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ToEditorAction extends ActionSupport {

	public String execute() {
		String name=null;
		Map<String, Object> session = null;
//		获取session
		session=ActionContext.getContext().getSession();
		name=(String) session.get("name");
		if(null==name){
			return "login";
		}else {
			
			return "editor";
		}
	}
}
