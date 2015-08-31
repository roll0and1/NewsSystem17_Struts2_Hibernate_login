package com.qiangge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.qiangge.dao.NewsDao;
import com.qiangge.model.News;
import com.qiangge.utils.AppException;
import com.qiangge.utils.DBUtil;

public class NewsDaoImpl implements NewsDao {
	@Override
	public boolean add(News news) throws AppException {
		// 操作标志
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection(); // 创建数据库连接
		// 声明操作语句：将用户信息保存到数据库中， “？”为占位符
		String sql = "insert into t_news(user_id,newsType_id,title,author,keywords,source,content,createTime,state) values (?,?,?,?,?,?,?,?,?);";
		try {
			// bug
			psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			psmt.setInt(1, news.getUserId());
			psmt.setInt(2, news.getNewsTypeId());
			psmt.setString(3, news.getTitle());
			psmt.setString(4, news.getAuthor());
			psmt.setString(5, news.getKeywords());
			psmt.setString(6, news.getSource());
			psmt.setString(7, news.getContent());
			psmt.setString(8, news.getCreateTime());
			psmt.setInt(9, news.getState());
			// 执行更新
			psmt.executeUpdate();
			// 得到插入行的主键，结果中只有一条记录
			rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				// 获取主键的值并设置到news对象中（添加附件时会用到）
				news.setId(rs.getInt(1));
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.add");
		} finally {
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}

		return flag;
	}

	@Override
	public List<News> getList(int state) throws AppException {
		List<News> newsList = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select id ,title from t_news where state=? and del =0 order by createTime desc;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			rs = psmt.executeQuery();
			// 循环提取结果集中的信息，保存到newList中
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				newsList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		return newsList;
	}
}
