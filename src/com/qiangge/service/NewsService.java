package com.qiangge.service;

import java.util.List;

import com.qiangge.dao.NewsDao;
import com.qiangge.dao.impl.NewsDaoImpl;
import com.qiangge.model.News;
import com.qiangge.model.NewsModel;
import com.qiangge.model.PageModel;
import com.qiangge.utils.AppException;

/**
 * 消息业务处理类
 * 
 * @author zha_zha
 * 
 */
public class NewsService {
	private NewsDao newsDao = new NewsDaoImpl();

	/**
	 * 创建新闻
	 * 
	 * @param news
	 * @return
	 * @throws AppException
	 */
	public boolean create(News news) throws AppException {
		boolean flag = false;
		flag = newsDao.add(news);
		return flag;
	}

	/**
	 * 获取相应用户的新闻列表
	 * 
	 * @param state
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	public List<NewsModel> getList(int state, int userId) throws AppException {
		List<NewsModel> list = null;
		list = newsDao.getList(state, userId);
		return list;
	}

	/**
	 * 获取相应用户的PageModel
	 * 
	 * @param state
	 * @param userId
	 * @param currentPage
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getList(int state, int userId, int currentPage, int size)
			throws AppException {
		PageModel pageModel = new PageModel();
		List<NewsModel> newsList = null;
		try {

			// 获取总记录数
			int totalCount = newsDao.getCount(state, userId);

			pageModel.setTotalCount(totalCount);

			// 分页查询新闻信息
			newsList = newsDao.getList(state, userId, currentPage, size);

			pageModel.setNewsList(newsList);

			pageModel.setCurrentPage(currentPage);
			pageModel.setSize(size);

		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException("com.qiangge.NewService.getList");
		}
		return pageModel;
	}

	/**
	 * 获取news信息
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	public News preview(int id) throws AppException {
		News news = null;
		try {
			news = newsDao.getNewsById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException("com.qiangge.NewService.preview");
		}
		return news;
	}

	public PageModel getList(int state, int currentPage, int size)
			throws AppException {
		PageModel pageModel = new PageModel();
		List<NewsModel> newsList = null;
		try {

			// 获取总记录数
			int totalCount = newsDao.getCount(state);

			pageModel.setTotalCount(totalCount);

			// 分页查询新闻信息
			newsList = newsDao.getList(state, currentPage, size);

			pageModel.setNewsList(newsList);

			pageModel.setCurrentPage(currentPage);
			pageModel.setSize(size);

		} catch (Exception e) {
			// TODO: handle exception
			throw new AppException("com.qiangge.NewService.getList");
		}
		return pageModel;
	}

	public boolean check(int state, int id) throws AppException {
		boolean flag = false;
		try {
			flag = newsDao.update(state, id);
		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.check");
		}
		return flag;
	}

	public List<News> getHotNews(int state, int num) throws AppException {
		List<News> hotNewsList = null;
		try {
			hotNewsList = newsDao.getHotNewsByClick(state, num);
		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.getHotNews");
		}
		return hotNewsList;
	}

	public List<News> getLatestNews(int state, int num) throws AppException {
		List<News> latestNewsList = null;
		try {
			latestNewsList = newsDao.getlatestNewsByCreateTime(state, num);
		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.latestNewsList");
		}
		return latestNewsList;
	}

	/**
	 * 获取首页相应类型新闻列表
	 * 
	 * @param i
	 * @param state
	 * @param num
	 * @return
	 * @throws AppException
	 */
	public List<News> getNewsByType(int i, int state, int num)
			throws AppException {

		List<News> typeNewsList = null;
		try {
			typeNewsList = newsDao.getTypeNews(i, state, num);
		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.latestNewsList");
		}
		return typeNewsList;
	}

	/**
	 * 
	 * @param state
	 * @param newsTypeId
	 * @param currentPage
	 * @param size
	 * @return
	 * @throws AppException
	 */
	public PageModel getTypeNews(int state, int newsTypeId, int currentPage,
			int size) throws AppException {
		PageModel typeNewsPageModel = new PageModel();

		try {
			// 获取新闻总数目
			int totalCount = newsDao.getCountByType(state, newsTypeId);
			// 获取每页新闻信息
			List<News> typeNewList = newsDao.getTypeNewList(state, newsTypeId,
					currentPage, size);

			typeNewsPageModel.setTotalCount(totalCount);
			typeNewsPageModel.setCurrentPage(currentPage);
			typeNewsPageModel.setSize(size);

		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.getTypeNews");
		}
		return typeNewsPageModel;
	}

	public boolean updateClick(int id) throws AppException {
		boolean flag = false;
		try {

			flag = newsDao.updateClick(id);
		} catch (Exception e) {
			throw new AppException("com.qiangge.NewService.updateClick");
		}
		return flag;
	}
}
