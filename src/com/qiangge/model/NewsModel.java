package com.qiangge.model;

/**
 * 新闻业务实体类
 * 
 */
public class NewsModel {
	private int id; // 新闻id
	private String title; // 新闻标题
	private String newsType;// 新闻类型
	private String creator;// 录入者
	private String source;// 新闻来源
	private String createTime;// 创建时间

	/**
	 * 创建无参构造函数，并为属性赋初值
	 * 
	 * @param id
	 * @param title
	 * @param newsType
	 * @param creator
	 * @param source
	 * @param createTime
	 */
	public NewsModel() {
		this.id = 0;
		this.title = "";
		this.newsType = "";
		this.creator = "";
		this.source = "";
		this.createTime = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
