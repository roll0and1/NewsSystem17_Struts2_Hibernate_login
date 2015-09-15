package com.qiangge.dao;

import java.util.List;

import com.qiangge.model.News;
import com.qiangge.model.NewsModel;
import com.qiangge.utils.AppException;

public interface NewsDao {
	boolean add(News news) throws AppException;

	List<NewsModel> getList(int state, int userId) throws AppException;

	int getCount(int state, int userId) throws AppException;

	int getCount(int state) throws AppException;

	List<NewsModel> getList(int state, int userId, int currentPage, int size)
			throws AppException;

	List<NewsModel> getList(int state, int currentPage, int size)
			throws AppException;

	News getNewsById(int id) throws AppException;

	boolean update(int state, int id) throws AppException;

	List<News> getHotNewsByClick(int state, int num) throws AppException;

	List<News> getlatestNewsByCreateTime(int state, int num)
			throws AppException;

	List<News> getTypeNews(int newTypeId, int state, int num) throws AppException;

	int getCountByType(int state, int newsTypeId) throws AppException;

	List<News> getTypeNewList(int state, int newsTypeId, int currentPage,
			int size) throws AppException;

	boolean updateClick(int id) throws AppException;

}
