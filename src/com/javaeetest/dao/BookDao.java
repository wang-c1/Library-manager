package com.javaeetest.dao;

import java.util.List;
import java.util.Map;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.Book;

public interface BookDao extends BaseDao<Book>{
	
	/**
	 * 向数据库中添加图书
	 * @param book
	 */
	public void addBook(Book book);
	
	/**
	 * 按页查询图书的信息
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Book> queryByPage(int offset,int pageSize,String searchCriteria);
	
	/**
	 * 得到所有正在售卖的图书的行数
	 * @param hql
	 * @return
	 */
	public int getAllRowCount(String searchCriteria);
	
	/**
	 * 更改图书的状态
	 * @param bookId
	 */
	public int updateBookStatus(int bookId);
	
	/**
	 * 根据图书的名称查询符合条件的图书
	 * @return
	 */
	public List<Book> searchBookByBookName(String searchCriteria);
	
	/**
	 * 根据图书的ID得到图书对象
	 * @param bookId
	 * @return
	 */
	public Book getBookByBookId(int bookId);
	
	/**
	 * 得到所有类别的图书的销售数量
	 * @return
	 */
	public Map<String,Integer> getTotoalSalesForEveryType();
	
	/**
	 * 更改图书的剩余数量和销售数量
	 * @param bookId
	 * @param differ
	 * @return
	 */
	public int updateBookAmountAndBookSales(int bookId,int differ);
	
	/**
	 * 更改图书的打折状态(如果正在打折则改为不打折，如果不在打折则改为打折)
	 * @return
	 */
	public int updateBookBargainStatus(int bookId,int status);
	
	/**
	 * 向数据库中增加图书数量
	 * @param bookId
	 * @param increment
	 * @return
	 */
	public int updateBookAmout(int bookId,int increment);
	
}
