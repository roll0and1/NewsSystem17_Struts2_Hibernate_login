package com.qiangge.utils;

public class AppException extends Exception {
	private int exceptionCode; // 异常编号
	private String message;// 异常消息

	public AppException(int exceptionCode, String message) {
		this.message = message;
		this.exceptionCode = exceptionCode;
	}

	public AppException(String message) {
		this.message = message;
	}

	public int getExceptionCode() {
		return exceptionCode;
	}

	public String getMessage() {
		return message;
	}
}
