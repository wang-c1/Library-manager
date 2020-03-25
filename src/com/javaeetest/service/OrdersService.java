package com.javaeetest.service;

import java.util.List;

import com.javaeetest.entity.Orders;
import com.javaeetest.entity.PageBean;

public interface OrdersService {

	/**
	 * 管理员查看所有的订单（包括已处理的和未处理的）
	 * 
	 * @return
	 */
	public List<Orders> getAllOrdersForManager();

	/**
	 * 根据订单的状态查询订单(status=1代表得到已处理的订单， status=0代表得到未处理的订单)
	 * 
	 * @param status
	 * @return
	 */
	public List<Orders> getOrdersForManagerForStatus(int status);

	/**
	 * 处理订单，把订单的状态变为已处理
	 * 
	 * @param orderId
	 * @return
	 */
	public boolean handleOrders(int orderId);

	/**
	 * 得到分页所需的数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean<Orders> getPageBean(int pageSize, int page, int status);

	/**
	 * 取消订单
	 * 
	 * @param ordersId
	 * @return
	 */
	public boolean cancelOrders(int ordersId);

	public Orders findOrders(int id);
}
