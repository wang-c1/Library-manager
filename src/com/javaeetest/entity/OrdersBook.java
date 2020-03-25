package com.javaeetest.entity;

import com.javaeetest.entity.Book;

public class OrdersBook {
	private Integer orderBookId;
	private Orders order;
	private Book book;
	private Integer bookAmount;

	public OrdersBook(Orders order, Book book, Integer bookAmount) {
		this.order = order;
		this.book = book;
		this.bookAmount = bookAmount;
	}

	public OrdersBook() {
	}

	public Integer getOrderBookId() {
		return orderBookId;
	}

	public void setOrderBookId(Integer orderBookId) {
		this.orderBookId = orderBookId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getBookAmount() {
		return bookAmount;
	}

	public void setBookAmount(Integer bookAmount) {
		this.bookAmount = bookAmount;
	}

	
}
