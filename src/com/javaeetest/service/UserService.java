package com.javaeetest.service;

import java.util.List;

import com.javaeetest.entity.PageBean;
import com.javaeetest.entity.User;

public interface UserService {
	
	/**
	 * 得到所有的用户
	 * @return 返回所有的用户存放于list中
	 */
	public List<User> getAllUser();
	
	/**
	 * 得到分页所需的数据
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean<User> getPageBean(int pageSize,int page);
	
	/**
	 * 根据用户 的ID得到用户的详细信息
	 * @param userId
	 * @return
	 */
	public User getUserInforByUserId(int userId);
	
	public void deleteUser(int userId);
	

}
