package com.javaeetest.service;

import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;

import com.javaeetest.entity.Book;
import com.javaeetest.entity.PageBean;

public interface BookService {
	
	/**
	 * 向数据库中添加图书
	 * @param book
	 */
	public void addBook(Book book);
	
	public void deleteBook(int bookId);
	
	public void deleteBarginBook(int bookId);
	/**
	 * 得到分页所需的数据
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public PageBean<Book> getPageBean(int pageSize,int page,String searchCriteria);
	
	/**
	 * 图书下架，更改图书的状态为0
	 * @param bookId
	 * @return
	 */
	public boolean offselvesBook(int bookId);
	
	/**
	 * 在添加图书页面搜索符合条件的图书添加特价
	 * @return
	 */
	public List<Book> getBookBySearchCriteriaInBargain(String searchCriteria);
	
	/**
	 * 根据图书id得到图书对象
	 * @param bookId
	 * @return
	 */
	public Book getBookByBookId(int bookId);
	
	/**
	 * 得到每类图书销售量
	 * @return
	 */
	public DefaultPieDataset getTotoalSalesForEveryTypeDataset();
	
	/**
	 * 更改图书的特价状态
	 * @param bookId
	 * @param status
	 * @return
	 */
	public boolean updateBookBargainStatus(int bookId,int status);
	
	/**
	 * 增加图书数量
	 * @param bookId
	 * @param increment
	 * @return
	 */
	public boolean addBookAmount(int bookId,int increment);
	
}
