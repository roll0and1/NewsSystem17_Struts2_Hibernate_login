package com.qiangge.web;

/**
 * 用户注册Action
 */
import com.opensymphony.xwork2.ActionSupport;
import com.qiangge.model.User;
import com.qiangge.service.UserService;
import com.qiangge.utils.AppException;

public class RegisterAction extends ActionSupport {

	private String name; // 用户名
	private String password;// 密码
	private String message; // 提示信息

	// 初始化用户业务逻辑类
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 处理用户注册：注册成功则跳转到登录页面； 否则，返回注册页面，给出提示信息
	 */
	public String execute() {
		boolean flag = false;
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
				return "login";
			} else {
				message = "注册失败";
				return "register";
			}
		} catch (AppException e) {
			e.printStackTrace();
			// 系统异常
			return "error";
		}

	}

}
