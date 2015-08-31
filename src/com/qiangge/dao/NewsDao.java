package com.qiangge.dao;

import java.util.List;

import com.qiangge.model.News;
import com.qiangge.utils.AppException;

public interface NewsDao {
	boolean add(News news) throws AppException;

	List<News> getList(int stage) throws AppException;
}
