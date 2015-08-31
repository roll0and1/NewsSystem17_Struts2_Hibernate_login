package com.qiangge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qiangge.dao.UserDao;
import com.qiangge.model.User;
import com.qiangge.utils.AppException;
import com.qiangge.utils.DBUtil;

public class UserDaoImpl implements UserDao {

	public boolean isExist(String name) throws AppException {
		// 操作标志
		boolean flag = true;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符
		String sql = "select * from t_user where name=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			if (rs.next() == false) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.isExist");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return flag;
	}

	public boolean add(User user) throws AppException {
		// 操作标志
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符
		String sql = "insert into t_user(name,password) values (?,?);";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getPassword());
			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	@Override
	public int login(String name, String password) throws AppException {
		// 初始化用户id
		int id = -1;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符 注意del
		String sql = "select id from t_user where name=? and password=? and del=0;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.login");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return id;

	}

	@Override
	public int getRoleById(int id) throws AppException {
		// 初始化用户role
		int role = -1;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符 注意del
		String sql = "select role from t_user where id=?;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				role = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.getRoleById");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return role;
	}
}
