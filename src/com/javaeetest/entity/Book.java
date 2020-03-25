package com.javaeetest.entity;

import java.sql.Timestamp;

public class Book {
	private Integer bookId;
	private String bookISBN;
	private String bookName;
	private String bookAuthor;
	private String bookPress;
	private String bookPicture;
	private Integer bookAmount;
	private BookType type;
	private Timestamp bookShelveTime;
	private Float bookPrice;
	private String bookRemark;
	private Integer bookSales;
	private Integer bookStatus;
	private Integer bargainStatus;
	private Float bookNewPrice;
	
	

	public Book() {
		super();
	}

	public Book(String bookISBN, String bookName, String bookAuthor, String bookPress,
			String bookPicture, Integer bookAmount, BookType type, Timestamp bookShelveTime, Float bookPrice,
			String bookRemark, Integer bookSales,Integer bookStatus,Integer bargainStatus) {
		this.bookISBN = bookISBN;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPress = bookPress;
		this.bookPicture = bookPicture;
		this.bookAmount = bookAmount;
		this.type = type;
		this.bookShelveTime = bookShelveTime;
		this.bookPrice = bookPrice;
		this.bookRemark = bookRemark;
		this.bookSales = bookSales;
		this.bookStatus = bookStatus;
		this.bargainStatus = bargainStatus;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPress() {
		return bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	public String getBookPicture() {
		return bookPicture;
	}

	public void setBookPicture(String bookPicture) {
		this.bookPicture = bookPicture;
	}

	public Integer getBookAmount() {
		return bookAmount;
	}

	public void setBookAmount(Integer bookAmount) {
		this.bookAmount = bookAmount;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public Timestamp getBookShelveTime() {
		return bookShelveTime;
	}

	public void setBookShelveTime(Timestamp bookShelveTime) {
		this.bookShelveTime = bookShelveTime;
	}

	public Float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookRemark() {
		return bookRemark;
	}

	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}

	public Integer getBookSales() {
		return bookSales;
	}

	public void setBookSales(Integer bookSales) {
		this.bookSales = bookSales;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getBargainStatus() {
		return bargainStatus;
	}

	public void setBargainStatus(Integer bargainStatus) {
		this.bargainStatus = bargainStatus;
	}

	public Float getBookNewPrice() {
		return bookNewPrice;
	}

	public void setBookNewPrice(Float bookNewPrice) {
		this.bookNewPrice = bookNewPrice;
	}

}
