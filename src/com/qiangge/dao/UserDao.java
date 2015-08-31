package com.qiangge.dao;

import com.qiangge.model.User;
import com.qiangge.utils.AppException;

/**
 * 
 * @author zha_zha
 *用户数据访问层接口，提供用户信息的保存和查询操作
 */
public interface UserDao {
	boolean isExist(String name) throws AppException;
	boolean add(User user) throws AppException;
	int login(String name,String password) throws AppException;
	int getRoleById(int id)throws AppException;
}
