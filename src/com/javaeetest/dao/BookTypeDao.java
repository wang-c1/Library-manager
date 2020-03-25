package com.javaeetest.dao;

import java.util.List;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.BookType;

public interface BookTypeDao extends BaseDao<BookType>{
	
	/**
	 * 根据类型名称得到类型对象
	 * @param typeName
	 */
	public BookType findBookTypeByTypeName(String typeName);
	
	/**
	 * 得到所有的图书类型的对象
	 * @return
	 */
	public List<BookType> getAllBookType();
	
	/**
	 * 根据图书类型的id得到图书类型对象
	 * @param typeId
	 * @return
	 */
	public BookType findBookTypeByTypeId(int typeId);
	
	/**
	 * 保存BookType实例
	 * @param bookType
	 */
	public void saveBookType(BookType bookType);
}
