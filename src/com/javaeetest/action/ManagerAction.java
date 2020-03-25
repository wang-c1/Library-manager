package com.javaeetest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.Manager;
import com.javaeetest.service.ManagerService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManagerAction extends ActionSupport implements
		ModelDriven<Manager> {

	private Manager model = new Manager();
	private ManagerService managerService;

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	/**
	 * 管理员登录
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int userId = managerService.loginValid(model);
		if (userId > 0) {
			if (session.getAttribute("managerLoginName") != null) {
				System.out.println("dsfs");
				return ERROR;
			} else {
				session.setAttribute("managerLoginName", model.getManagerName());
				// addActionMessage("登陆成功");
				return SUCCESS;
			}
		} else {
			addActionError("用户名密码不匹配");
			return INPUT;
		}
	}

	public String managerExit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("managerLoginName");
		return SUCCESS;
	}

	@Override
	public Manager getModel() {
		return model;
	}
}
