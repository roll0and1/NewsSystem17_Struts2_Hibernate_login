package com.qiangge.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qiangge.service.UserService;
import com.qiangge.utils.AppException;

public class LoginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 登录初步处理
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String message = "";
		if (name == null || "".equals(name) || password == null
				|| "".equals(password)) {
			System.out.println("---输入不正确---");
			System.out.println("用户名和密码不能为空！");
			message = "用户名或密码不能为空！";
			// 将message信息存入request
			request.setAttribute("message", message);
			// 登录失败转发到登录页面
			request.getRequestDispatcher("ToLogin").forward(request, response);
		} else {
			/**
			 * 进行登录处理根据用户名和用户密码来查询用户Id，此处采用硬编码的方式 登录成功返回用户Id值
			 */
			// 初始化用户id
			int userId = -1;
			try {
				// 初始化Userservice
				UserService userService = new UserService();
				userId = userService.login(name, password);

				if (userId > 0) {
					// 登录成功
					// 声明session
					HttpSession session = null;
					session = request.getSession();
					// 将用户Id和用户名保存到session中
					session.setAttribute("userId", userId);
					session.setAttribute("name", name);
					// 重定向到用户页面
					response.sendRedirect("ToNewUser");
				} else {
					// 登录失败跳转到登录页面
					message = "用户名或密码错误！";
					// 将message存入request中
					request.setAttribute("message", message);
					// 将本次登录的用户名传递到页面上显示
					request.setAttribute("name", name);
					// 将页面转发至登录页面
					request.getRequestDispatcher("ToLogin").forward(request,
							response);
				}

			} catch (AppException e) {
				e.printStackTrace();
				// 重定向到异常页面
				response.sendRedirect("ToError");
			}
		}

	}
}
