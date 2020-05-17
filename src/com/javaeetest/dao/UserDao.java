package com.javaeetest.dao;

import java.util.List;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.User;

public interface UserDao extends BaseDao<User>{
	
	/**
	 * 得到所有的用户信息
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * 按页查询用户的信息
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<User> queryByPage(int offset,int pageSize);
	
	/**
	 * 得到所有用户的行数
	 * @param hql
	 * @return
	 */
	public int getAllRowCount();
	
	/**
	 * 根据用户的ID得到用户对象
	 * @return
	 */
	public User findUserByUserId(int userId);
	
	public int deleteUser(int userId);
	

}
