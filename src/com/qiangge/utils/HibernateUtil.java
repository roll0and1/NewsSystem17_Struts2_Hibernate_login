package com.qiangge.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory(new ServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).buildServiceRegistry());
	}

	// 获取session
	public static Session getSession() {
		return sf.openSession();
	}

	// 关闭session
	public static void closeSession(Session session) {
		if (null != session || session.isOpen()) {
			session.close();
		}
	}

}
