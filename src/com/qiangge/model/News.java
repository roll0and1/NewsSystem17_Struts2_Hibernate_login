package com.qiangge.model;

public class News {
	private int id; // ID
	private String title; // 新闻标题
	private String author; // 作者姓名
	private int newsTypeId;// 新闻类型id
	private int userId; // 录入者id
	private String keywords; // 关键词
	private String source;// 新闻来源
	private String content; // 新闻正文
	private String createTime; // 创建时间
	private int click; // 点击次数
	private int state; // 状态位（0，未审核； 1，已发布；2，已废除）
	private int del; // 删除状态（0，未删除；1，已删除）

	// 无参构造方法
	// 用于初始化私有属性，给属性赋初值。若属性不初始化，那么未赋值的属性在层与层之间传递的过程中，
	// 可能会出现空指针异常；在页面显示未赋值时，会出现空值（null），对用户很不友好

	public News() {
		super();
		this.id = 0;
		this.title = "";
		this.author = "";
		this.newsTypeId = 0;
		this.userId = 0;
		this.keywords = "";
		this.source = "";
		this.content = "";
		this.createTime = "";
		this.click = 0;
		this.state = 0;
		this.del = 0;
	}

	public String getAuthor() {
		return author;
	}

	public int getClick() {
		return click;
	}

	public String getContent() {
		return content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public int getDel() {
		return del;
	}

	public int getId() {
		return id;
	}

	public String getKeywords() {
		return keywords;
	}

	public int getNewsTypeId() {
		return newsTypeId;
	}

	public String getSource() {
		return source;
	}

	public int getState() {
		return state;
	}

	public String getTitle() {
		return title;
	}

	public int getUserId() {
		return userId;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
