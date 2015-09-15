package com.qiangge.model;

/**
 * 新闻业务实体类
 * 
 */
public class NewsModel {
	private int id; // 新闻id
	private String title; // 新闻标题

	private String name;// 新闻类型

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
		this.name = "";
		this.creator = "";
		this.source = "";
		this.createTime = "";
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getCreator() {
		return creator;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSource() {
		return source;
	}

	public String getTitle() {
		return title;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
