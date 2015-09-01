package com.qiangge.dao;

import java.util.List;

import com.qiangge.model.News;
import com.qiangge.model.NewsModel;
import com.qiangge.utils.AppException;

public interface NewsDao {
	boolean add(News news) throws AppException;

	List<NewsModel> getList(int stage, int userId) throws AppException;

	int getCount(int stage, int userId) throws AppException;

	List<NewsModel> getList(int state, int userId, int currentPage, int size)
			throws AppException;

	News getNewsById(int id) throws AppException;
}
