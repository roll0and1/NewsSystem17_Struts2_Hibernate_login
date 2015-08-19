package com.qiangge.service;

import com.qiangge.dao.UserDao;
import com.qiangge.dao.impl.UserDaoImpl;
import com.qiangge.model.User;
import com.qiangge.utils.AppException;

/**
 * 
 * @author zha_zha 用户业务处理类，处理用户的注册业务逻辑
 */

public class UserService {
	private UserDao userDao = new UserDaoImpl();

	/**
	 * 
	 * @param user
	 * @return
	 * @throws AppException
	 *             调用数据访问层isExist（）验证同名注册，调用save（）保存用户信息
	 */
	public boolean regisetr(User user) throws AppException {
		boolean flag = false;
		try {
			boolean x = userDao.isExist(user.getName());
			if (!x) {
				// 保存用户信息
				flag = userDao.add(user);
			}
		} catch (AppException e) {
			throw new AppException("com.qiangge.service.UserService.register");
		}
		return flag;
	}

	public int login(String name, String password) throws AppException {
		int userId = -1;
		try {
			// 获取用户Id
			userId = userDao.login(name, password);
			return userId;

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.UserService.login");
		}
	}
}
