package com.javaeetest.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.Orders;
import com.javaeetest.entity.PageBean;
import com.javaeetest.service.OrdersService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrdersAction extends ActionSupport implements ModelDriven<Orders> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrdersService ordersService;
	private Orders model = new Orders();
	private int page;

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public Orders getModel() {
		return model;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	/**
	 * 显示所有订单信息
	 * 
	 * @return
	 */
	public String showAllOrders() {
		PageBean<Orders> pageBean = ordersService.getPageBean(10, page, 2);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("ordersPageBean", pageBean);
		request.setAttribute("status", 2);
		return SUCCESS;
	}

	/**
	 * 显示已经处理的订单
	 * 
	 * @return
	 */
	public String showIsDealOrders() {
		PageBean<Orders> pageBean = ordersService.getPageBean(10, page, 1);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("ordersPageBean", pageBean);
		request.setAttribute("status", 1);
		return SUCCESS;
	}

	/**
	 * 显示没有处理的订单
	 * 
	 * @return
	 */
	public String showIsNotDealOrders() {
		PageBean<Orders> pageBean = ordersService.getPageBean(10, page, 0);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("ordersPageBean", pageBean);
		request.setAttribute("status", 0);
		return SUCCESS;
	}

	/**
	 * 处理订单
	 * 
	 * @return
	 */
	public String handleOrder() {
		try {
			if (ordersService.handleOrders(model.getOrderId())) {
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				ordersService.cancelOrders(model.getOrderId());
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "ajax_succ";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
