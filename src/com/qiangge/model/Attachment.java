package com.qiangge.model;

public class Attachment {
	private int id;// id
	private int newsId;// 新闻id
	private String fileName;// 文件名称
	private String path;// 文件路径
	private String type;// 文件类型
	private String uploadTime;// 上传时间
	private int del;// 删除状态（0，未删除；1，已删除）
	public int getDel() {
		return del;
	}
	public String getFileName() {
		return fileName;
	}
	public int getId() {
		return id;
	}
	public int getNewsId() {
		return newsId;
	}
	public String getPath() {
		return path;
	}
	public String getType() {
		return type;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Attachment() {
		super();
		this.id = 0;
		this.newsId = 0;
		this.fileName = "";
		this.path = "";
		this.type = "";
		this.uploadTime = "";
		this.del = 0;
	}
	
}
