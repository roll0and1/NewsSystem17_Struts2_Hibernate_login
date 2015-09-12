package com.qiangge.service;

import com.qiangge.dao.UserDao;
import com.qiangge.model.User;
import com.qiangge.utils.AppException;

/**
 * 用户业务处理类，处理用户的注册等业务逻辑
 * 
 * @author zha_zha
 */

public class UserService {
	private UserDao userDao ;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

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

	public int getRole(int id) throws AppException {
		int role = -1;
		try {
			// 获取用户role
			role = userDao.getRoleById(id);
			return role;

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.UserService.getRole");
		}
	}
}
