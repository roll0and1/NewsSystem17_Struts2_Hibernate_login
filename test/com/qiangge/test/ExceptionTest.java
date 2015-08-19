package com.qiangge.test;

import org.junit.Test;

import com.qiangge.utils.AppException;

public class ExceptionTest {

	@Test
	public void test() throws AppException {
		boolean a=true;
		if(a){
			throw new AppException("com.qiangge.AppException is wrong!");
		}
	}

}
