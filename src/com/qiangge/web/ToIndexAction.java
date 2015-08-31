package com.qiangge.web;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 转到初始页面的action
 * 
 * @author zha_zha
 * 
 */
public class ToIndexAction extends ActionSupport {
	@Override
	public String execute() {
		return "index";
	}
}
