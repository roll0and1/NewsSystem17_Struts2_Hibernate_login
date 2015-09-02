package com.qiangge.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.User;
import com.qiangge.service.UserService;
import com.qiangge.utils.AppException;

public class UserAction extends ActionSupport {
	private String name;
	private String password;
	private String password2;

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	private String message; // 提示信息

	private UserService userService = new UserService();

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String login() {

		int userId = -1;

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
				return "toLogin";
			}
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public String logout() {
		// 移除session中的name和userId
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("name");
		session.remove("userId");
		return "logout";
	}

	/**
	 * 处理用户注册：注册成功则跳转到登录页面； 否则，返回注册页面，给出提示信息
	 */
	public String register() {
		if (null == name || null == password || null == password2) {
			return "register";
		}

		boolean flag = false;
		String result = null;
		// 实例化user实体类对象
		User user = new User();
		// 为user对象设置属性值
		user.setName(name);
		user.setPassword(password);
		try {
			// 调用业务逻辑进行用户注册
			flag = userService.regisetr(user);
			// 3.设置提示信息
			if (flag) {
				// 注册成功
				message = "注册成功";
				result = "toLogin";
			} else {
				message = "注册失败,用户名已存在";
				result = "register";
			}
			// 获取request
			Map<String, Object> request = (Map<String, Object>) ActionContext
					.getContext().get("request");
			// 将message 放入request
			request.put("message", message);
			return result;
		} catch (AppException e) {
			e.printStackTrace();
			// 系统异常
			return "error";
		}

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
