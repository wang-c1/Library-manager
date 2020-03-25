package com.javaeetest.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.PageBean;
import com.javaeetest.entity.User;
import com.javaeetest.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private int page;
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 用户信息列表展示
	 */
	@Override
	public String execute() throws Exception {

		PageBean<User> pageBean = userService.getPageBean(10, page);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("pageBean", pageBean);

		return SUCCESS;
	}

	/**
	 * 用户详细信息
	 * 
	 * @return
	 */
	public String singleUser() {
		User user = userService.getUserInforByUserId(userId);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("singleUser", user);

		return SUCCESS;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
