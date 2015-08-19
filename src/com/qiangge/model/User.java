package com.qiangge.model;

public class User {
	private int id; // 用户id
	private String name; // 用户名称
	private String password;// 用户密码
	private int role; // 角色（0，普通编辑；1，管理员）
	private int del; // 删除状态（0，未删除；1，已删除）

	public int getDel() {
		return del;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getRole() {
		return role;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public User() {
		super();
		this.id = 0;
		this.name = "";
		this.password = "";
		this.role = 0;
		this.del = 0;
	}

}
