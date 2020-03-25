package com.javaeetest.dao;

import java.util.List;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.Orders;

public interface OrdersDao extends BaseDao<Orders> {
	/**
	 * 管理员查看所有订单（包括已处理的和未处理的）
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
	 * 处理订单，把未处理的订单编程已处理
	 * 
	 * @param ordersId
	 * @return
	 */
	public int updateOrdersStatus(int orderId, int status);

	/**
	 * 得到分页所需的数据
	 * 
	 * @return
	 */
	public List<Orders> queryByPage(int offset, int pageSize, int status);

	/**
	 * 得到满足条件的行数
	 * 
	 * @return
	 */
	public int getAllRowCount(int status);

	public Orders findOrders(int id);

}
