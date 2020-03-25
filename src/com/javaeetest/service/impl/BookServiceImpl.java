package com.javaeetest.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.data.general.DefaultPieDataset;

import com.javaeetest.dao.BookDao;
import com.javaeetest.entity.Book;
import com.javaeetest.entity.PageBean;
import com.javaeetest.service.BookService;

public class BookServiceImpl implements BookService {
	private BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	/**
	 * 添加新书
	 * 
	 */
	@Override
	public void addBook(Book book) {
		try {
			bookDao.addBook(book);
		} catch (Exception e) {
			System.out.println("图书未添加成功");
			e.printStackTrace();
		}
	}

	@Override
	public PageBean<Book> getPageBean(int pageSize, int page,
			String searchCriteria) {
		PageBean<Book> pageBean = new PageBean<Book>();
		int allRows = bookDao.getAllRowCount(searchCriteria);
		int totalPage = pageBean.getTotalPages(pageSize, allRows);
		int currentPage = pageBean.getCurPage(page);
		int offset = pageBean.getCurrentPageOffset(pageSize, currentPage);
		List<Book> list = bookDao.queryByPage(offset, pageSize, searchCriteria);

		pageBean.setList(list);
		pageBean.setAllRows(allRows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}

	@Override
	public boolean offselvesBook(int bookId) {
		if (bookDao.updateBookStatus(bookId) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Book> getBookBySearchCriteriaInBargain(String searchCriteria) {
		return bookDao.searchBookByBookName(searchCriteria);
	}

	@Override
	public Book getBookByBookId(int bookId) {
		System.out.println("进入到bookservice中的getBookByBookId方法");
		return bookDao.getBookByBookId(bookId);
	}

	@Override
	public DefaultPieDataset getTotoalSalesForEveryTypeDataset() {
		Map<String, Integer> map = bookDao.getTotoalSalesForEveryType();
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		DefaultPieDataset dataset = new DefaultPieDataset();
		while (it.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>) it.next();
			dataset.setValue(entry.getKey(), entry.getValue());
			System.out.println("销量：" + entry.getValue());
		}
		return dataset;
	}

	@Override
	public boolean updateBookBargainStatus(int bookId, int status) {

		if (bookDao.updateBookBargainStatus(bookId, status) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addBookAmount(int bookId, int increment) {
		if (bookDao.updateBookAmout(bookId, increment) > 0) {
			return true;
		}
		return false;
	}

}
