package com.javaeetest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.BookTypeDao;
import com.javaeetest.entity.BookType;

public class BookTypeDaoImpl extends BaseDaoImpl<BookType> implements BookTypeDao{

	@Override
	public BookType findBookTypeByTypeName(String typeName) {
		List ul = find("SELECT * FROM tb_bookType WHERE "
				+ "typeName=?0", typeName);
		
		if(ul != null || ul.size() > 0){
			return (BookType)ul.get(0);
		}
		return null;
	}

	@Override
	public List<BookType> getAllBookType() {
		String hql = "from BookType order by typeId";
		List<BookType> typeList = find(hql);
		return typeList;
	}

	@Override
	public BookType findBookTypeByTypeId(int typeId) {
		String hql = "from BookType where typeId=?";
		List<BookType> list = find(hql, typeId);
		return list.get(0);
	}

	@Override
	public void saveBookType(BookType bookType) {
		save(bookType);
	}
	
	@Override
	public int deleteBookType(int typeId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "delete BookType where typeId = "
					+ typeId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("BookTypeDao中删除类别的状态出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}
}
