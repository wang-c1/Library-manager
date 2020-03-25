package com.javaeetest.dao;

import java.util.List;

import com.javaeetest.common.dao.BaseDao;
import com.javaeetest.entity.Bargain;
import com.javaeetest.entity.BargainBook;

public interface BargainDao extends BaseDao<Bargain>{
	
	/**
	 * 根据不同的status得到正在打折和打折结束的图书
	 * @param status (status=0取出的是特价已经结束的书，status=1取出的是特价正在进行的书)
	 * @return
	 */
	public List<Bargain> getBargainBookForDifferentStatus(int status);
	
	/**
	 * 添加打折图书
	 * @param bargain
	 */
	public void addBargainBook(Bargain bargain);
	
	/**
	 * 修改特价图书的状态，有正在进行特价变化为特价结束。
	 * @param bargain
	 */
	public int updateBargainStatus(int bargainId);
	
	/**
	 * 得到所有正在进行特价的图书
	 * @return
	 */
	public List<BargainBook> getAllBargainingBook();
	
	/**
	 * 根据bargainId得到bargain对象
	 * @param bargainId
	 * @return
	 */
	public Bargain getBargainByBargainId(int bargainId);
	
	/**
	 * 根据bookId得到该对象的打折信息
	 * @param bookId
	 * @return
	 */
	public Bargain getBargainByBookId(int bookId);
	
}
