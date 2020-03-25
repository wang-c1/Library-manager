package com.javaeetest.entity;

public class Bargain {
	private Integer bargainId;
	private Book book;
	private Float bookNewPrice;
	private Integer bargainStatus;

	public Bargain() {
		super();
	}

	public Bargain(Book book, Float bookNewPrice, Integer bargainStatus) {
		super();
		this.book = book;
		this.bookNewPrice = bookNewPrice;
		this.bargainStatus = bargainStatus;
	}

	public Integer getBargainId() {
		return bargainId;
	}

	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Float getBookNewPrice() {
		return bookNewPrice;
	}

	public void setBookNewPrice(Float bookNewPrice) {
		this.bookNewPrice = bookNewPrice;
	}

	public Integer getBargainStatus() {
		return bargainStatus;
	}

	public void setBargainStatus(Integer bargainStatus) {
		this.bargainStatus = bargainStatus;
	}

}
