package com.javaeetest.service.impl;

import java.util.List;

import com.javaeetest.dao.UserDao;
import com.javaeetest.entity.PageBean;
import com.javaeetest.entity.User;
import com.javaeetest.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * 得到所有的用户信息
	 */
	@Override
	public List<User> getAllUser() {
		List<User> list = null;
		try{
			list = userDao.findAllUser();
		}catch(Exception e){
			System.out.println("得到所有用户出现异常");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageBean getPageBean(int pageSize, int page) {
		PageBean<User> pageBean = new PageBean<User>();
		int allRows = userDao.getAllRowCount();
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		int currentPage = pageBean.getCurPage(page);
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<User> list = userDao.queryByPage(offset, pageSize);
		
		pageBean.setList(list);
        pageBean.setAllRows(allRows);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	@Override
	public User getUserInforByUserId(int userId) {
		return userDao.findUserByUserId(userId);
	}
	
	@Override
	public void deleteUser(int userId) {
		try {
			int a = userDao.deleteUser(userId);
			if(a == 1) {
				System.out.println("用户删除成功!");
			}
		} catch (Exception e) {
			System.out.println("用户未删除成功");
			e.printStackTrace();
		}
	}
	
}
