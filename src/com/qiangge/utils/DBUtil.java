package com.qiangge.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/newssystem";
	private static final String user = "root";
	private static final String password = "zhazha";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 创建数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);// 创建数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接成功");
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if ((conn != null) && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接关闭");
	}

	public static void closeStatement(Statement statement) {
		try {
			if ((statement != null) && !statement.isClosed()) {
				statement.close();
				statement = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		try {
			if ((resultSet != null) && !resultSet.isClosed()) {
				resultSet.close();
				resultSet = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
