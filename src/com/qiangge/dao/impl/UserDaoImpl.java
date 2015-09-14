package com.qiangge.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.qiangge.dao.UserDao;
import com.qiangge.model.User;
import com.qiangge.utils.AppException;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public boolean isExist(String name) throws AppException {
		// 操作标志
		boolean flag = false;
		try {
			// 定义hql语句，根据用户名查询记录数，？为占位符
			String hql = "from User u where u.del=0 and u.name=?";
			List list = this.getHibernateTemplate().find(hql, name);

			System.out.println("Size:" + list.size());
			if (list.size() > 0) {// 如果size>0，表示存在该用户，修改标识符为true
				flag = true;

			}
		} catch (Exception e) {
			throw new AppException("com.qiangge.dao.impl.isExist");
		}

		return flag;
	}

	public boolean add(User user) throws AppException {
		// 操作标志
		boolean flag = false;
		try {

			this.getHibernateTemplate().save(user);

			System.out.println("持久化对象的id为：" + user.getId());

			// 获取用户的id，若大于0表示添加成功
			if (user.getId() > 0) {
				flag = true;
			}

		} catch (Exception e) {
			// throw new AppException("com.qiangge.dao.impl.add");
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 通过判断用户id来确定用户可否登录
	 */
	@Override
	public int login(String name, String password) throws AppException {
		// 初始化用户id
		int id = -1;
		// 获取session
		try {
			String hql = "from User u where u.name=? and u.password=? and u.del=0";
			List<User> uList = (List<User>) this.getHibernateTemplate().find(
					hql, new String[] { name, password });
			if (uList.size() != 0) {
				id = uList.get(0).getId();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.login");
		}
		return id;

	}

	/**
	 * 判断用户是编辑还是管理员
	 */
	@Override
	public int getRoleById(int id) throws AppException {
		// 初始化用户role
		int role = -1;

		try {
			String hql = "from User u where u.id=?";

			List<User> u = (List<User>) this.getHibernateTemplate().find(hql,
					id);

			if (u != null) {
				role = u.get(0).getRole();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.getRoleById");
		}
		return role;
	}

}
