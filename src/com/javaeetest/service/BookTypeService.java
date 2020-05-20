package com.javaeetest.service;

import java.util.List;

import com.javaeetest.entity.BookType;

public interface BookTypeService {
	
	/**
	 * 调用dao中的方法得到BookType的实体
	 * @param typeName
	 * @return
	 */
	public BookType getBookTypeByTypeName(String typeName);
	
	/**
	 * 调用dao中的方法得到所有BookType的实体
	 * @return
	 */
	public List<BookType> getAllBookType();
	
	/**
	 * 验证类型名称是否可用，如果系统中已经存在该用户名，则不可用
	 * @param typeName
	 * @return 用户名是否可用
	 */
	public boolean validateTypeName(String typeName);
	
	/**
	 * 向数据库添加图书类型
	 * @param bookType
	 * @return(true:保存成功，false:保存失败)
	 */
	public boolean addBookType(BookType bookType);
	
	public void deleteBookType(int typeId);
}
