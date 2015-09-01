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
	 * 获取相应的新闻列表
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
	 * 获取PageModel
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
}
