package com.javaeetest.service;

import java.util.List;

import com.javaeetest.entity.Bargain;
import com.javaeetest.entity.BargainBook;

public interface BargainService {
	
	/**
	 * 得到所有正在进行特价销售的书
	 * @return
	 */
	public List<BargainBook> getAllBargainingBook();
	
	/**
	 * 得到所有已经打完折的书
	 * @return
	 */
	public List<BargainBook> getAllBargainedBook();
	
	/**
	 * 把正在进行特价的图书停止
	 * @param bookId
	 * @return
	 */
	public boolean noBargainBook(int bargainId);
	
	/**
	 * 添加要进行打折的书
	 * @param bargain
	 */
	public boolean addBargainBook(Bargain bargain);
	
	/**
	 * 得到某本书的打折信息
	 * @param bookId
	 * @return
	 */
	public Bargain getBargainByBookId(int bookId);
	
}
