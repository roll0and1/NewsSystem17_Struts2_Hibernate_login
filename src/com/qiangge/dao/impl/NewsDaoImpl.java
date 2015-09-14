package com.qiangge.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.qiangge.dao.NewsDao;
import com.qiangge.model.News;
import com.qiangge.model.NewsModel;
import com.qiangge.utils.AppException;
import com.qiangge.utils.DBUtil;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {
	/**
	 * 添加新闻
	 */
	@Override
	public boolean add(News news) throws AppException {
		// 操作标志
		boolean flag = false;
		try {
			
			this.getHibernateTemplate().save(news);

			if (news.getId() > 0) {
				// 获取主键的值并设置到news对象中（添加附件时会用到）
				news.setId(news.getId());
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.add");

		}
		return flag;
	}

	/**
	 * 获取用户的新闻
	 */
	@Override
	// 查询时，本人没有开启事务 ???
	public List<NewsModel> getList(int state, int userId) throws AppException {
		Session session = null;
		List<NewsModel> newsList = new ArrayList<NewsModel>();
		String sql = "select t_news.id ,t_news.title,t_newstype.name,t_news.createTime,t_news.source "
				+ "from t_news,t_newstype"
				+ " where "
				+ "t_news.state=? and t_news.user_id=? "
				+ "and t_news.newsType_id=t_newstype.id" + " and t_news.del=0;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, userId);
			Iterator<?> iterator = query.iterate();
			// 循环提取结果集中的信息，保存到newList中
			while (iterator.hasNext()) {
				NewsModel newsModel = (NewsModel) iterator.next(); // 实例化对象

				newsList.add(newsModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
		} finally {
			session.close();
		}
		System.out.println(newsList.size());
		return newsList;
	}

	/**
	 * 获取用户当前页面新闻
	 */

	@Override
	public List<NewsModel> getList(int state, int userId, int currentPage,
			int size) throws AppException {
		List<NewsModel> newsList = new ArrayList<NewsModel>();
		Session session = null;
		String sql = "select t_news.id ,t_news.title,t_newstype.name,t_news.createTime,t_news.source "
				+ "from t_news,t_newstype"
				+ " where "
				+ "t_news.state=? and t_news.user_id=? "
				+ "and t_news.newsType_id=t_newstype.id"
				+ " and t_news.del=0 "
				+ "limit ?,?;";
//		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(1, state);
			query.setInteger(2, userId);

			// 计算起始位置
			int offset = (currentPage - 1) * size;
			query.setInteger(3, offset);
			query.setInteger(4, size);
			Iterator<?> iterator = query.iterate();
			// 循环提取结果集中的信息，保存到newList中
//			while (rs.next()) {
//				NewsModel newsModel = new NewsModel(); // 实例化对象
//				newsModel.setId(rs.getInt(1));
//				newsModel.setTitle(rs.getString(2));
//				newsModel.setNewsType(rs.getString(3));
//				// 截取前19个字符 ，否则会显示出“.0”
//				String createTime = rs.getString(4).substring(0, 19);
//				newsModel.setCreateTime(createTime);
//				newsModel.setSource(rs.getString(5));
//				newsList.add(newsModel);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
//		} finally {
//			DBUtil.closeResultSet(rs);
//			DBUtil.closeStatement(psmt);
//			DBUtil.closeConnection(conn);
//		}
		System.out.println(newsList.size());
		System.out.println("state" + state);
		System.out.println("userId:" + userId);
		System.out.println("currentPage:" + currentPage);
		return null;
	}

	@Override
	public int getCount(int state, int userId) throws AppException {
		int count = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();

		String sql = "select count(id) as n from t_news where user_id=? and state=? and del=0;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userId);
			psmt.setInt(2, state);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getCount");
		}
		return count;
	}

	@Override
	public News getNewsById(int id) throws AppException {
		News news = new News();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select author,title,createTime,source,click,content "
				+ "from t_news where id=? and del=0;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				news.setAuthor(rs.getString(1));
				news.setTitle(rs.getString(2));
				news.setCreateTime(rs.getString(3).substring(0, 19));
				news.setSource(rs.getString(4));
				news.setClick(rs.getInt(5));
				news.setContent(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.getRoleById");
		}
		return news;
	}

	@Override
	public List<NewsModel> getList(int state, int currentPage, int size)
			throws AppException {
		List<NewsModel> newsList = new ArrayList<NewsModel>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select t_news.id ,t_news.title,t_news.createTime,t_newstype.name,t_user.name "
				+ "from t_news,t_newstype,t_user"
				+ " where "
				+ "t_news.state=? "
				+ "and t_news.newsType_id=t_newstype.id"
				+ " and t_user.id=t_news.user_id "
				+ " and t_news.del=0 "
				+ "limit ?,?;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			// 计算起始位置
			int offset = (currentPage - 1) * size;
			psmt.setInt(2, offset);
			psmt.setInt(3, size);
			rs = psmt.executeQuery();
			// 循环提取结果集中的信息，保存到newList中
			while (rs.next()) {
				NewsModel newsModel = new NewsModel(); // 实例化对象
				newsModel.setId(rs.getInt(1));
				newsModel.setTitle(rs.getString(2));
				// 截取前19个字符 ，否则会显示出“.0”
				String createTime = rs.getString(3).substring(0, 19);
				newsModel.setCreateTime(createTime);
				newsModel.setNewsType(rs.getString(4));
				newsModel.setCreator(rs.getString(5));
				newsList.add(newsModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		}
		System.out.println(newsList.size());
		System.out.println("state" + state);
		System.out.println("currentPage:" + currentPage);
		return newsList;
	}

	@Override
	public int getCount(int state) throws AppException {

		int count = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();

		String sql = "select count(id) as n from t_news where state=? and del=0;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getCount");
		}
		return count;
	}

	@Override
	public boolean update(int state, int id) throws AppException {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		conn = DBUtil.getConnection();
		String sql = "update t_news set state=? where id=? and del=0";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			psmt.setInt(2, id);
			psmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new AppException("com.qiangge.dao.impl.NewsImpl.update");
		}
		return flag;
	}

	@Override
	public List<News> getHotNewsByClick(int state, int num) throws AppException {
		List<News> hotNewsList = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select id,title from t_news where state=? order by click desc limit ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			psmt.setInt(2, num);
			rs = psmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				hotNewsList.add(news);
			}
		} catch (Exception e) {
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getHotNewsByClick");
		}
		return hotNewsList;
	}

	@Override
	public List<News> getlatestNewsByCreateTime(int state, int num)
			throws AppException {
		List<News> hotNewsList = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select id,title from t_news where state=? order by createTime desc limit ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			psmt.setInt(2, num);
			rs = psmt.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				hotNewsList.add(news);
			}
		} catch (Exception e) {
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getHotNewsByClick");
		}
		return hotNewsList;
	}

	@Override
	public List<News> getTypeNews(int i, int state, int num)
			throws AppException {
		List<News> typeNewsList = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select id,title from t_news where newsType_id=? and state=? order by createTime limit ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, i);
			psmt.setInt(2, state);
			psmt.setInt(3, num);
			rs = psmt.executeQuery();
			while (rs.next()) {
				News news = new News();

				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				typeNewsList.add(news);
			}
		} catch (Exception e) {
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getTypeNews");
		}
		System.out.println("typeId" + i + "size：" + typeNewsList.size());
		return typeNewsList;
	}

	@Override
	public int getCountByType(int state, int newsTypeId) throws AppException {
		int count = 0;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();

		String sql = "select count(id) as n from t_news where state=? and newsType_id=? and del=0;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			psmt.setInt(2, newsTypeId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getCountByType");
		}
		return count;
	}

	@Override
	public List<News> getTypeNewList(int state, int newsTypeId,
			int currentPage, int size) throws AppException {
		List<News> newsList = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "select id,title,createTime,click " + "from t_news "
				+ " where " + "state=? and newsType_id=? and del=0 "
				+ "order by createTime desc " + "limit ?,?;";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, state);
			psmt.setInt(2, newsTypeId);
			// 计算起始位置
			int offset = (currentPage - 1) * size;
			psmt.setInt(3, offset);
			psmt.setInt(4, size);
			rs = psmt.executeQuery();
			// 循环提取结果集中的信息，保存到newList中
			while (rs.next()) {
				News news = new News(); // 实例化对象
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				// 截取前10个字符
				String createTime = rs.getString(3).substring(0, 10);
				news.setCreateTime(createTime);
				news.setClick(rs.getInt(4));
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
		System.out.println("getTypeNewList-size:" + newsList.size());
		System.out.println("getTypeNewList-state:" + state);
		System.out.println("getTypeNewList-newsTypeId:" + newsTypeId);
		System.out.println("getTypeNewList-currentPage:" + currentPage);
		return newsList;
	}

	@Override
	public boolean updateClick(int id) throws AppException {

		boolean flag = false;
		Connection conn = null;
		PreparedStatement psmt = null;
		conn = DBUtil.getConnection();
		String sql = "update t_news set click=click+1 where id=? and del=0";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new AppException("com.qiangge.dao.impl.NewsImpl.updateClick");
		}
		return flag;
	}

}
