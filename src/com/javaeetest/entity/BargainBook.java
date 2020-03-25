package com.javaeetest.entity;

public class BargainBook {
	private Integer bargainId;
	private Double bookNewPrice;
	private String bookISBN;
	private String bookName;
	private String bookAuthor;
	private String bookPress;
	private Double bookPrice;

	
	
	public BargainBook(Integer bargainId, Double bookNewPrice, String bookISBN, String bookName, String bookAuthor,
			String bookPress, Double bookPrice) {
		super();
		this.bargainId = bargainId;
		this.bookNewPrice = bookNewPrice;
		this.bookISBN = bookISBN;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPress = bookPress;
		this.bookPrice = bookPrice;
	}

	public BargainBook() {
	}

	public Integer getBargainId() {
		return bargainId;
	}

	public void setBargainId(Integer bargainId) {
		this.bargainId = bargainId;
	}

	public Double getBookNewPrice() {
		return bookNewPrice;
	}

	public void setBookNewPrice(Double bookNewPrice) {
		this.bookNewPrice = bookNewPrice;
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

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

}
