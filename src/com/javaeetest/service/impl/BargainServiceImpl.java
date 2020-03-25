package com.javaeetest.service.impl;

import java.util.List;

import com.javaeetest.dao.BargainDao;
import com.javaeetest.dao.BookDao;
import com.javaeetest.entity.Bargain;
import com.javaeetest.entity.BargainBook;
import com.javaeetest.service.BargainService;

public class BargainServiceImpl implements BargainService {

	private BargainDao bargainDao;
	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBargainDao(BargainDao bargainDao) {
		this.bargainDao = bargainDao;
	}

	public BargainDao getBargainDao() {
		return bargainDao;
	}

	/**
	 * 得到所有正在进行特价销售的书
	 * 
	 * @return
	 */
	@Override
	public List<BargainBook> getAllBargainingBook() {
		List<BargainBook> list = null;
		try {
			list = bargainDao.getAllBargainingBook();
			for (BargainBook bargainBook : list) {
				System.out.println(bargainBook.getBookName() + " ds");
			}
		} catch (Exception e) {
			System.out.println("得到所有正在打折的书出现异常");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 得到所有已经打完折的书
	 * 
	 * @return
	 */
	@Override
	public List<BargainBook> getAllBargainedBook() {
		List<BargainBook> list = null;
		try {
			list = bargainDao.getAllBargainingBook();
		} catch (Exception e) {
			System.out.println("得到所有正在打折的书出现异常");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean noBargainBook(int bargainId) {
		Bargain bargain = bargainDao.getBargainByBargainId(bargainId);
		int bs = bargainDao.updateBargainStatus(bargainId);
		int bbs = bookDao.updateBookBargainStatus(
				bargain.getBook().getBookId(), 1);

		System.out.println("bs:" + bs + " bbs:" + bbs);
		if (bs > 0 && bbs > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addBargainBook(Bargain bargain) {
		try {
			if (bookDao.updateBookBargainStatus(bargain.getBook().getBookId(),
					0) > 0) {
				bargainDao.save(bargain);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BargainServiceImpl:添加打折图书出现异常");
		}
		return false;
	}

	@Override
	public Bargain getBargainByBookId(int bookId) {
		return bargainDao.getBargainByBookId(bookId);
	}

}
