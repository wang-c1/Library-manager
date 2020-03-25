package com.javaeetest.service.impl;

import java.util.List;

import com.javaeetest.dao.OrdersBookDao;
import com.javaeetest.entity.OrdersBook;
import com.javaeetest.service.OrdersBookService;

public class OrdersBookServiceImpl implements OrdersBookService{
	private OrdersBookDao ordersBookDao;

	public OrdersBookDao getOrdersBookDao() {
		return ordersBookDao;
	}

	public void setOrdersBookDao(OrdersBookDao ordersBookDao) {
		this.ordersBookDao = ordersBookDao;
	}

	@Override
	public List<OrdersBook> getOrdersBookByOrderId(int orderId) {
		return ordersBookDao.getOrdersBookByOrderId(orderId);
	}


}
