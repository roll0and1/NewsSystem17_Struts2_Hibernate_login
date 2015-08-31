package com.qiangge.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.service.UserService;
import com.qiangge.utils.AppException;

public class LoginAction extends ActionSupport {
	private String name;
	private String password;

	private UserService userService = new UserService();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() {

		int userId = -1;
		String message = null;
		HttpServletRequest request = null;
		Map<String, Object> session = null;
		// 得到Aciton执行的上下文
		ActionContext context = ActionContext.getContext();

		try {
			userId = userService.login(name, password);
			if (userId > 0) {
				// 将用户id和name存入session
				// 获取map类型的session对象
				session = context.getSession();

				// 将userId和name存入session
				session.put("name", name);
				session.put("userId", userId);
				// 获取用户role
				int role = -1;
				role = userService.getRole(userId);
				if (role == 0) {
					return "toEditor";
				} else {
					return "toAdmin";
				}

			} else {
				message = "用户名或密码错误";
				// 获取request
				request = (HttpServletRequest) context
						.get(ServletActionContext.HTTP_REQUEST);
				// 将message存入request
				request.setAttribute("message", message);
				return "login";
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

}
