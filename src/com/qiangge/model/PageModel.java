package com.qiangge.model;

import java.util.List;

/**
 * 
 * 封装分页信息
 */
public class PageModel {
	private List<News> typeNewList;

	// 结果集
	private List<NewsModel> newsList;

	// 查询的总记录数
	private int totalCount;

	// 每页记录数
	private int size;
	// 当前所处页数
	private int currentPage;

	public PageModel() {
		this.currentPage = 1;
		this.size = 5;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}

	/**
	 * 末页
	 * 
	 * @return
	 */
	public int getLastPage() {
		return this.getTotalpages();
	}

	public List<NewsModel> getNewsList() {
		return newsList;
	}

	/**
	 * 后一页
	 * 
	 * @return
	 */
	public int getNextPage() {
		if (this.currentPage == this.getTotalpages()) {
			return this.getTotalpages();
		}
		return currentPage + 1;
	}

	/**
	 * 前一页
	 * 
	 * @return
	 */
	public int getPrePage() {
		if (this.currentPage <= 1) {
			return 1;
		}
		return currentPage - 1;
	}

	public int getSize() {
		return size;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalpages() {

		return (this.totalCount + this.size - 1) / this.size;
	}

	public List<News> getTypeNewList() {
		return typeNewList;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setNewsList(List<NewsModel> newsList) {
		this.newsList = newsList;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTypeNewList(List<News> typeNewList) {
		this.typeNewList = typeNewList;
	}
}
