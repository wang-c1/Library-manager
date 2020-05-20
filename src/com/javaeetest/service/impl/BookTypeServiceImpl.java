package com.javaeetest.service.impl;

import java.util.List;

import com.javaeetest.dao.BookTypeDao;
import com.javaeetest.entity.BookType;
import com.javaeetest.service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService {

	private BookTypeDao bookTypeDao;

	public BookTypeDao getBookTypeDao() {
		return bookTypeDao;
	}

	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	@Override
	public BookType getBookTypeByTypeName(String typeName) {
		try {
			BookType type = bookTypeDao.findBookTypeByTypeName(typeName);
			if (type != null) {
				return type;
			}
		} catch (Exception e) {
			System.out.println("得到图书类型根据类型名称出现错误");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookType> getAllBookType() {
		try {
			List<BookType> typeList = bookTypeDao.getAllBookType();
			if (typeList != null) {
				return typeList;
			}
		} catch (Exception e) {
			System.out.println("得到所有图书类型出现错误");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证类型名是否可用(true:可用，false:不可用)
	 */
	@Override
	public boolean validateTypeName(String typeName) {
		try {
			BookType bookType = bookTypeDao.findBookTypeByTypeName(typeName);
			if (bookType != null) {
				System.out.println("service里：" + bookType.getTypeName());
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("验证类型id是否可用时出现异常");
		}
		return true;
	}

	/**
	 * 添加图书类别
	 */
	@Override
	public boolean addBookType(BookType bookType) {
		try {
			bookTypeDao.saveBookType(bookType);
		} catch (Exception e) {
			System.out.println("添加图书类型出现错误");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public void deleteBookType(int typeId) {
		try {
			int a = bookTypeDao.deleteBookType(typeId);
			if(a == 1) {
				System.out.println("用户删除成功!");
			}
		} catch (Exception e) {
			System.out.println("用户未删除成功");
			e.printStackTrace();
		}
	}

}
