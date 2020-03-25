package com.javaeetest.service.impl;

import java.util.List;

import com.javaeetest.dao.BookDao;
import com.javaeetest.dao.OrdersBookDao;
import com.javaeetest.dao.OrdersDao;
import com.javaeetest.entity.Book;
import com.javaeetest.entity.Orders;
import com.javaeetest.entity.OrdersBook;
import com.javaeetest.entity.PageBean;
import com.javaeetest.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {
	private OrdersDao ordersDao = null;
	private OrdersBookDao ordersBookDao = null;
	private BookDao bookDao = null;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public OrdersBookDao getOrdersBookDao() {
		return ordersBookDao;
	}

	public void setOrdersBookDao(OrdersBookDao ordersBookDao) {
		this.ordersBookDao = ordersBookDao;
	}

	public OrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	@Override
	public List<Orders> getAllOrdersForManager() {
		return ordersDao.getAllOrdersForManager();
	}

	@Override
	public List<Orders> getOrdersForManagerForStatus(int status) {
		return ordersDao.getOrdersForManagerForStatus(status);
	}

	@Override
	public boolean handleOrders(int orderId) {

		List<OrdersBook> list = ordersBookDao.getOrdersBookByOrderId(orderId);
		int updateAmoutResult = 1;
		int updateOrders = 1;
		int flag = 1;
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i).getBook();
			if (book.getBookAmount() < list.get(i).getBookAmount()) {
				flag = 0;
				break;
			}
		}

		if (flag == 0) {
			return false;
		} else {
			for (int i = 0; i < list.size(); i++) {
				updateAmoutResult = updateAmoutResult
						* bookDao.updateBookAmountAndBookSales(list.get(i)
								.getBook().getBookId(), list.get(i)
								.getBookAmount());
			}
			if (updateAmoutResult > 0) {
				updateOrders = ordersDao.updateOrdersStatus(orderId, 1);
			} else {
				return false;
			}
		}

		if (updateAmoutResult * updateOrders > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageBean<Orders> getPageBean(int pageSize, int page, int status) {
		PageBean<Orders> pageBean = new PageBean<Orders>();
		int allRows = ordersDao.getAllRowCount(status);
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		int currentPage = pageBean.getCurPage(page);
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<Orders> list = ordersDao.queryByPage(offset, pageSize, status);

		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	@Override
	public boolean cancelOrders(int ordersId) {
		if (ordersDao.updateOrdersStatus(ordersId, -1) > 0) {
			return true;
		}
		return false;
	}

	public Orders findOrders(int id) {
		return ordersDao.findOrders(id);
	}
}
