package com.javaeetest.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.BookDao;
import com.javaeetest.entity.Book;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	/**
	 * 添加新书
	 * 
	 */
	@Override
	public void addBook(Book book) {
		Session session = sessionFactory.openSession();
		save(book);
	}
	
	@Override
	public int deleteBook(int bookId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "delete Book where bookId = "
					+ bookId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("BookDao中删除图书的状态出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}
	
	@Override
	public int deleteBarginBook(int bookId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "delete Bargin where bookId = "
					+ bookId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("BookDao中删除图书的状态出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}

	@Override
	public List<Book> queryByPage(int offset, int pageSize,
			String searchCriteria) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Book> list = null;
		StringBuffer sb = new StringBuffer();
		try {

			sb.append("from Book ");
			if (searchCriteria != null && searchCriteria.length() > 0) {
				sb.append("where bookName like " + "'%" + searchCriteria + "%'");
			}
			System.out.println("dao里：" + sb.toString());
			tx = session.beginTransaction();
			Query query = session.createQuery(sb.toString());
			if (query.list().size() != 0) {
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}

			if (query != null) {
				list = query.list();
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int getAllRowCount(String searchCriteria) {

		StringBuffer sb = new StringBuffer();
		sb.append("from Book ");
		if (searchCriteria != null && searchCriteria.length() > 0) {
			sb.append("where bookName like " + "'%" + searchCriteria + "%'");
		}
		int allRows = find(sb.toString()).size();
		return allRows;
	}

	/**
	 * 更改图书是否在卖的状态
	 * 
	 * @param bookId
	 */
	@Override
	public int updateBookStatus(int bookId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "update Book set bookStatus = 0 where bookId = "
					+ bookId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("BookDao中更改图书的状态出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}

	@Override
	public List<Book> searchBookByBookName(String searchCriteria) {
		Session session = sessionFactory.openSession();
		String hql = "from Book where bookName like " + "'%" + searchCriteria
				+ "%'" + " and bookStatus=1 and bargainStatus=0";
		Query query = session.createQuery(hql);
		if (query.list().size() == 0) {
			return null;
		}
		List<Book> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Book getBookByBookId(int bookId) {
		List ul = find("SELECT * FROM tb_book WHERE " + "bookId=?0", bookId);

		if (ul != null || ul.size() > 0) {
			return (Book) ul.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Integer> getTotoalSalesForEveryType() {
		Session session = sessionFactory.openSession();
		String sql = "select b.typeName as typeNam,SUM(bookSales) as total"
				+ " from tb_book a left join tb_bookType b on a.typeId=b.typeId"
				+ " GROUP BY a.typeId,b.typeName" + " ORDER BY total ASC";
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		Map<String, Integer> map = new HashMap<String, Integer>();
		System.out.println("map.size:" + map.size());
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			map.put((String) obj[0], (Integer) obj[1]);
		}
		session.close();
		return map;
	}

	@Override
	public int updateBookAmountAndBookSales(int bookId, int differ) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int count = 0;
		try {
			tx = session.beginTransaction();
			String hql = "update Book set bookAmount=bookAmount-" + differ
					+ " ,bookSales=bookSales+" + differ + " where bookId="
					+ bookId;
			Query query = session.createQuery(hql);
			count = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("更改图书的数量销售数量出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int updateBookBargainStatus(int bookId, int status) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		String hql;
		int count = 0;
		try {
			tx = session.beginTransaction();
			if (status == 0) {
				hql = "update Book set bargainStatus=1 where bookId=" + bookId;
			} else {
				hql = "update Book set bargainStatus=0 where bookId=" + bookId;
			}
			Query query = session.createQuery(hql);
			count = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}

	@Override
	public int updateBookAmout(int bookId, int increment) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int count = 0;
		try {
			tx = session.beginTransaction();
			String hql = "update Book set bookAmount=bookAmount+" + increment
					+ " where bookId=" + bookId;
			Query query = session.createQuery(hql);
			count = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			count = 0;
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}
}
