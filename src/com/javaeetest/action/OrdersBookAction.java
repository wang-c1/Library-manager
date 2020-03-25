package com.javaeetest.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.javaeetest.entity.Address;
import com.javaeetest.entity.Book;
import com.javaeetest.entity.Orders;
import com.javaeetest.entity.OrdersBook;
import com.javaeetest.service.BargainService;
import com.javaeetest.service.OrdersBookService;
import com.javaeetest.service.OrdersService;
import com.javaeetest.service.impl.AddressServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrdersBookAction extends ActionSupport implements
		ModelDriven<OrdersBook> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrdersBookService ordersBookService;
	private OrdersBook model = new OrdersBook();
	private int orderId;
	private BargainService bargainService;
	private AddressServiceImpl addressService;
	private OrdersService ordersService;

	public AddressServiceImpl getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressServiceImpl addressService) {
		this.addressService = addressService;
	}

	public void setBargainService(BargainService bargainService) {
		this.bargainService = bargainService;
	}

	public BargainService getBargainService() {
		return bargainService;
	}

	public OrdersBookService getOrdersBookService() {
		return ordersBookService;
	}

	public void setOrdersBookService(OrdersBookService ordersBookService) {
		this.ordersBookService = ordersBookService;
	}

	@Override
	public OrdersBook getModel() {
		return model;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String execute() throws Exception {
		List<OrdersBook> list = ordersBookService
				.getOrdersBookByOrderId(orderId);
		HttpServletRequest request = ServletActionContext.getRequest();

		Book book = null;
		for (int i = 0; i < list.size(); i++) {
			book = list.get(i).getBook();
			if (book.getBargainStatus() == 0) {
				book.setBookNewPrice(0f);
			} else {
				book.setBookNewPrice(bargainService.getBargainByBookId(
						book.getBookId()).getBookNewPrice());
			}
			list.get(i).setBook(book);
		}
		request.setAttribute("ordersBookList", list);
		Float totalMoney = list.get(0).getOrder().getTotalMoney();
		Timestamp orderTime = list.get(0).getOrder().getOrderTime();
		int status = list.get(0).getOrder().getOrderStatus();
		request.setAttribute("orderTimeInOrdersBookAction", orderTime);
		request.setAttribute("totalMoneyInOrdersBookAction", totalMoney);
		request.setAttribute("orderStatusInOrdersBookAction", status);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getOrder().getOrderTime());
			System.out.println(list.get(i).getOrder().getTotalMoney());
		}

		Address address = new Address();
		// System.out.println(orders.getAddressId());
		System.out.println(orderId);

		Orders orders = ordersService.findOrders(orderId);
		System.out.println(orders.getAddressId());
		address = addressService.findAdderssById(orders.getAddressId());

		System.out.println(address.getDetailedAddress() + "ssdsd");
		/*
		 * request.setAttribute("bookList", bookList);
		 * request.setAttribute("orders", orders);
		 */
		request.setAttribute("address", address);
		return SUCCESS;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

}
