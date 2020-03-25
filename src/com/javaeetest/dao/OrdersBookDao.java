package com.javaeetest.dao;

import java.util.List;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.OrdersBook;

public interface OrdersBookDao extends BaseDao<OrdersBook> {
	
	/**
	 * 根据订单ID得到该订单对应的图书
	 * @param orderId
	 */
	public List<OrdersBook> getOrdersBookByOrderId(int orderId);

}
