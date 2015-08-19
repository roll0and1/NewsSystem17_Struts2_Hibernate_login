package com.qiangge.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.qiangge.utils.DBUtil;

public class DBConnectionTest {
	@Test
	public void test() {
		boolean flag = false;
		Connection conn = DBUtil.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符
		String sql = "select * from t_user where name=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "xiaomianyang");
			rs = psmt.executeQuery();
			System.out.println(rs.next());
			if (rs.next() == false) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		System.out.println(flag);

	}

	@Test
	public void testAdd() {
		Connection conn = DBUtil.getConnection();
		PreparedStatement psmt = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符
		String sql = "insert into t_user(name,password) values (?,?);";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "zhangqiang");
			psmt.setString(2, "zhangqiang");
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

	}
}
