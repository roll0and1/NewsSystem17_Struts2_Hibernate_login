package com.qiangge.model;

public class NewsType {
	private int id; // id
	private String name; // 类别名称
	private String description;// 描述
	private String createTime;// 创建时间
	private int orders;// 用于排序，默认0不排序
	private int del;// 删除状态（0，未删除；1，已删除）

	public String getCreateTime() {
		return createTime;
	}

	public int getDel() {
		return del;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getOrders() {
		return orders;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public NewsType() {
		super();
		this.id = 0;
		this.name = "";
		this.description = "";
		this.createTime = "";
		this.orders = 0;
		this.del = 0;
	}
	
}
