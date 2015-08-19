package com.qiangge.web;

/**
 * 接受注册页面提交的注册信息，并进行注册处理
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiangge.model.User;
import com.qiangge.service.UserService;
import com.qiangge.utils.AppException;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	// 声明注册处理标识
	boolean flag = false;
	// 初始化提示信息
	String message = "";
	// 实例化user实体类对象
	User user = new User();
	// 初始化用户业务逻辑类
	UserService userService = new UserService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名、密码和重复密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if (null == name || "".equals(name) || null == password
				|| "".equals(password) || null == password2
				|| "".equals(password2)) {
			System.out.println("注册信息错误！");
			message = "注册失败";
		} else if (!password.equals(password2)) {

			System.out.println("注册信息错误，密码不一致！");
			message = "注册失败";
		} else {

			System.out.println("注册成功！");
			System.out.println("用户名为：" + name);
			System.out.println("密码为：" + password);
			try {
				// 处理请求
				// 1.封装注册信息
				// 将用户信息封装到user对象中
				user.setName(name);
				user.setPassword(password);
				// 2.调用业务逻辑层处理
				// 调用业务逻辑进行用户注册
				flag = userService.regisetr(user);
				// 3.设置提示信息
				if (flag) {
					// 注册成功
					message = "注册成功";
					System.out.println("注册成功");
				} else {
					message = "注册失败";
					System.out.println("注册失败");
				}
			} catch (AppException e) {
				e.printStackTrace();
				// 系统异常
				message = "系统异常";
			}

		}
		// 4.传递到页面
		// 将message保存到request中
		request.setAttribute("message", message);
		// 转发到注册页面
		request.getRequestDispatcher("/ToRegister").forward(request, response);
	}
}
