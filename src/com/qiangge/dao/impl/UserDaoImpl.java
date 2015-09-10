package com.qiangge.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

import com.qiangge.dao.UserDao;
import com.qiangge.model.User;
import com.qiangge.utils.AppException;
import com.qiangge.utils.HibernateUtil;

public class UserDaoImpl implements UserDao {

	public boolean isExist(String name) throws AppException {
		// 操作标志
		boolean flag = false;
		try {
			// 定义hql语句，根据用户名查询记录数，？为占位符
			String hql = "from User u where u.del=0 and u.name=?";
			// 1.读取配置文件hibernate.hbm.xml初始化SessionFactory
			SessionFactory sf = null;
			Configuration cfg = new Configuration().configure();
			sf = cfg.buildSessionFactory(new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry());
			System.out.println("sf:" + sf);
			// 2.获取session
			Session session = sf.openSession();
			System.out.println("session:" + session);
			// 3.获取query对象
			Query query = session.createQuery(hql);
			System.out.println("query:" + query);
			// 设置占位符
			query.setString(0, name);
			// 4.进行查询
			List list = query.list();

			System.out.println("Size:" + list.size());
			if (list.size() > 0) {// 如果size>0，表示存在该用户，修改标识符为true
				flag = true;
			}
			// 关闭session
			if (session != null && session.isOpen()) {
				session.close();
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

			// 1.读取配置文件hibernate.hbm.xml初始化SessionFactory
			SessionFactory sf = null;
			Configuration cfg = new Configuration().configure();
			sf = cfg.buildSessionFactory(new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry());
			// 2.获取session
			Session session = sf.openSession();
			// 3.获取事务
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();

			System.out.println("持久化对象的id为：" + user.getId());

			// 获取用户的id，若大于0表示添加成功
			if (user.getId() > 0) {
				flag = true;
			}
			// 关闭session
			if (session != null && session.isOpen()) {
				session.close();
			}
		} catch (Exception e) {
			throw new AppException("com.qiangge.dao.impl.add");
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
			Session session = HibernateUtil.getSession();
			String hql = "from User u where u.name=? and u.password=? and u.del=0";
			Query query = session.createQuery(hql);
			query.setString(0, name);
			query.setString(1, password);
			User u = (User) query.uniqueResult();
			if(u!=null){
				id=u.getId();
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
			String hql = "from User u where u.id=?;";
			Session session = HibernateUtil.getSession();
			// 执行查询
			Query query = session.createQuery(hql);
			query.setInteger(0, id);
			Integer roleNum = (Integer) query.uniqueResult();
			if (roleNum != null) {
				role = roleNum;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.getRoleById");
		}
		return role;
	}

}
