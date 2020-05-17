package com.javaeetest.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.Book;
import com.javaeetest.entity.PageBean;
import com.javaeetest.entity.User;
import com.javaeetest.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private int page;
	private int userId;
	private User model = new User();

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
	 * 删除用户
	 * 
	 * @return
	 */
	public String deleteUser() {
		//System.out.println("进入更改图书函数action");
		//System.out.println("bookId:" + model.getBookId());
		HttpServletRequest request = ServletActionContext.getRequest();
		//int increment = Integer.parseInt(request.getParameter("increment"));
		//bookService.deleteBarginBook(model.getBookId());
		int userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("userId:" + userId);
		userService.deleteUser(userId);
		System.out.println(userId);
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
