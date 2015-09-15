package com.qiangge.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.qiangge.dao.NewsDao;
import com.qiangge.model.News;
import com.qiangge.model.NewsModel;
import com.qiangge.utils.AppException;

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
	public List<NewsModel> getList(int state, int userId) throws AppException {
		List<NewsModel> newsList = new ArrayList<NewsModel>();
		Session session = null;
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
			query.setResultTransformer(Transformers
					.aliasToBean(NewsModel.class));
			List<NewsModel> list = (List<NewsModel>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (NewsModel newsModel : list) {

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
	 * 获取某一用户当前页面新闻
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
				+ " and t_news.del=0 order by t_news.createTime desc "
				+ "limit ?,?;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, userId);

			// 计算起始位置
			int offset = (currentPage - 1) * size;
			query.setInteger(2, offset);
			query.setInteger(3, size);

			query.setResultTransformer(Transformers
					.aliasToBean(NewsModel.class));
			List<NewsModel> list = (List<NewsModel>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (NewsModel newsModel : list) {

				newsList.add(newsModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println(newsList.size());
			System.out.println("state" + state);
			System.out.println("userId:" + userId);
			System.out.println("currentPage:" + currentPage);
		}
		return newsList;
	}

	@Override
	public int getCount(int state, int userId) throws AppException {
		int count = 0;
		Session session = null;
		System.out.println("get count state:" + state);
		String sql = "select count(id) as n from t_news where user_id=? and state=? and del=0;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql).addScalar("n",
					StandardBasicTypes.INTEGER);

			query.setInteger(0, userId);
			query.setInteger(1, state);

			count = (int) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getCount");
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public News getNewsById(int id) throws AppException {
		News news = null;
		Session session = null;
		String sql = "select author,title,createTime,source,click,content "
				+ "from t_news where id=? and del=0;";
		// String hql = "from News news where news.id=?";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, id);
			query.setResultTransformer(Transformers.aliasToBean(News.class));
			news = (News) query.list().get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.getRoleById");
		} finally {
			session.close();
		}
		return news;
	}

	/**
	 * 待审核的新闻列表
	 */
	@Override
	public List<NewsModel> getList(int state, int currentPage, int size)
			throws AppException {
		List<NewsModel> newsList = new ArrayList<NewsModel>();
		Session session = null;
		System.out.println("getList state=" + state);
		String sql = "select t_news.id as id ,t_news.title as title,t_news.createTime as createTime,"
				+ "t_newstype.name as name,t_user.name as creator"
				+ " from t_news,t_newstype,t_user "
				+ "where t_news.state=? and"
				+ " t_news.newsType_id=t_newstype.id and t_user.id=t_news.user_id"
				+ " and t_user.id=t_news.user_id and t_news.del=0 "
				+ "order by createTime desc" + " limit ?,?;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);

			// 计算起始位置
			int offset = (currentPage - 1) * size;
			query.setInteger(1, offset);
			query.setInteger(2, size);

			query.setResultTransformer(Transformers
					.aliasToBean(NewsModel.class));
			List<NewsModel> list = (List<NewsModel>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (NewsModel newsModel : list) {

				newsList.add(newsModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
		} finally {
			session.close();
		}
		System.out.println(newsList.size());
		System.out.println("state" + state);
		System.out.println("currentPage:" + currentPage);
		return newsList;
	}

	/**
	 * 处于某状态的新闻条数
	 */
	@Override
	public int getCount(int state) throws AppException {

		int count = 0;
		Session session = null;
		String sql = "select count(id) as n from t_news where state=? and del=0;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql).addScalar("n",
					StandardBasicTypes.INTEGER);
			query.setInteger(0, state);
			count = (int) query.list().get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getCount");
		} finally {
			session.close();
		}
		System.out.println("count:" + count);
		return count;
	}

	/**
	 * 更新新闻状态
	 */

	@Override
	public boolean update(int state, int id) throws AppException {
		boolean flag = false;
		Session session = null;
		String sql = "update t_news set state=? where id=? and del=0";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, id);
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.update");
		} finally {
			session.close();
		}
		return flag;
	}

	/**
	 * 热门新闻列表
	 */
	@Override
	public List<News> getHotNewsByClick(int state, int num) throws AppException {
		List<News> hotNewsList = new ArrayList<News>();
		Session session = null;
		System.out.println("getHotNewsByClick state:" + state);
		String sql = "select id,title from t_news where state=? order by click desc limit ? ";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, num);

			query.setResultTransformer(Transformers.aliasToBean(News.class));
			List<News> list = (List<News>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (News hotNews : list) {

				hotNewsList.add(hotNews);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getHotNewsByClick");
		} finally {
			session.close();
		}
		System.out.println("hotNewsList:" + hotNewsList.size());
		return hotNewsList;
	}

	/**
	 * 最近发布的新闻列表
	 */
	@Override
	public List<News> getlatestNewsByCreateTime(int state, int num)
			throws AppException {
		List<News> latestNewsList = new ArrayList<News>();
		Session session = null;

		String sql = "select id,title from t_news where state=? order by createTime desc limit ? ";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, num);

			query.setResultTransformer(Transformers.aliasToBean(News.class));
			List<News> list = (List<News>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (News latestNews : list) {

				latestNewsList.add(latestNews);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getHotNewsByClick");
		} finally {
			session.close();
		}
		System.out.println("latestNewsList:" + latestNewsList.size());
		return latestNewsList;
	}

	/**
	 * 获取一定数目的分类新闻
	 */
	@Override
	public List<News> getTypeNews(int newTypeId, int state, int num)
			throws AppException {
		List<News> typeNewsList = new ArrayList<News>();
		Session session = null;

		String sql = "select id,title from t_news where newsType_id=? and state=? order by createTime limit ? ";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, newTypeId);
			query.setInteger(1, state);
			query.setInteger(2, num);

			query.setResultTransformer(Transformers.aliasToBean(News.class));
			List<News> list = (List<News>) query.list();
			// 循环提取结果集中的信息，保存到newList中
			for (News typeNews : list) {

				typeNewsList.add(typeNews);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getTypeNews");
		} finally {
			session.close();
		}
		System.out
				.println("typeId" + newTypeId + "size：" + typeNewsList.size());
		return typeNewsList;
	}

	/**
	 * 获取处于某种状态下的某分类新闻的数目
	 */
	@Override
	public int getCountByType(int state, int newsTypeId) throws AppException {
		int count = 0;
		Session session = null;

		String sql = "select count(id) as n from t_news where state=? and newsType_id=? and del=0;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql).addScalar("n",
					StandardBasicTypes.INTEGER);

			query.setInteger(0, state);
			query.setInteger(1, newsTypeId);
			count = (int) query.list().get(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException(
					"com.qiangge.dao.impl.NewsImpl.getCountByType");
		} finally {
			session.close();
		}
		System.out.println("count:" + count);
		return count;
	}

	/**
	 * 获取分页的分类新闻
	 */
	@Override
	public List<News> getTypeNewList(int state, int newsTypeId,
			int currentPage, int size) throws AppException {
		Session session = null;
		List<News> newsList = new ArrayList<News>();
		String sql = "select id,title,createTime,click " + "from t_news "
				+ " where " + "state=? and newsType_id=? and del=0 "
				+ "order by createTime desc " + "limit ?,?;";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, state);
			query.setInteger(1, newsTypeId);
			int offset = (currentPage - 1) * size;
			query.setInteger(2, offset);
			// 计算起始位置
			query.setInteger(3, size);
			query.setResultTransformer(Transformers.aliasToBean(News.class));
			List<News> list = (List<News>) query.list();
			for (News news : list) {
				newsList.add(news);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.getList");
		} finally {
			session.close();
		}
		System.out.println("getTypeNewList-size:" + newsList.size());
		System.out.println("getTypeNewList-state:" + state);
		System.out.println("getTypeNewList-newsTypeId:" + newsTypeId);
		System.out.println("getTypeNewList-currentPage:" + currentPage);
		return newsList;
	}

	/**
	 * 更新新闻的点击数
	 */
	@Override
	public boolean updateClick(int id) throws AppException {

		boolean flag = false;
		Session session = null;
		String sql = "update t_news set click=click+1 where id=? and del=0";
		try {
			session = this.getSessionFactory().openSession();
			Query query = session.createSQLQuery(sql);
			query.setInteger(0, id);
			query.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("com.qiangge.dao.impl.NewsImpl.updateClick");
		} finally {
			session.close();
		}
		return flag;
	}

}
