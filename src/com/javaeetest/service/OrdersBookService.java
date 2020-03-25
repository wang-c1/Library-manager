package com.javaeetest.service;

import java.util.List;

import com.javaeetest.entity.OrdersBook;

public interface OrdersBookService {
	
	/**
	 * 根据订单ID得到该订单对应的图书
	 * @param orderId
	 */
	public List<OrdersBook> getOrdersBookByOrderId(int orderId);

}
