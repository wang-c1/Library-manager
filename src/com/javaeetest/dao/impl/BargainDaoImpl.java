package com.javaeetest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.BargainDao;
import com.javaeetest.entity.Bargain;
import com.javaeetest.entity.BargainBook;

public class BargainDaoImpl extends BaseDaoImpl<Bargain> implements BargainDao {

	/**
	 * 根据不同的status得到正在打折和打折结束的图书
	 * 
	 * @param status
	 *            (status=0取出的是特价已经结束的书，status=1取出的是特价正在进行的书)
	 * @return
	 */
	@Override
	public List<Bargain> getBargainBookForDifferentStatus(int status) {
		String hql = "from Bargain where bargainStatus=" + status;
		List<Bargain> resultList = find(hql);
		return resultList;
	}

	/**
	 * 添加打折图书
	 * 
	 * @param bargain
	 */
	@Override
	public void addBargainBook(Bargain bargain) {
		save(bargain);
	}

	/**
	 * 修改特价图书的状态，有正在进行特价变化为特价结束。
	 * 
	 * @param bargain
	 */
	@Override
	public int updateBargainStatus(int bargainId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "update Bargain set bargainStatus=0 where bargainId="
					+ bargainId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BargainBook> getAllBargainingBook() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<BargainBook> resultList = new ArrayList<BargainBook>();
		try {
			String Sql = "select bargain.bargainId,bargain.bookNewPrice"
					+ ",book.bookISBN"
					+ ",book.bookName,book.bookPress"
					+ ",book.bookAuthor,book.bookPrice"
					+ " from tb_book as book,tb_bargain as bargain "
					+ " where bargain.bookId=book.bookId and bargain.bargainStatus=1 order by bargainId asc";
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(Sql);
			List list = query.list();

			for (int i = 0; i < list.size(); i++) {
				BargainBook bb = new BargainBook();
				Object[] obj = (Object[]) list.get(i);
				bb.setBargainId((Integer) obj[0]);
				bb.setBookNewPrice((Double) obj[1]);
				bb.setBookISBN((String) obj[2]);
				bb.setBookName((String) obj[3]);
				bb.setBookPress((String) obj[4]);
				bb.setBookAuthor((String) obj[5]);
				bb.setBookPrice((Double) obj[6]);
				resultList.add(bb);
				System.out.println(bb.getBookName());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		for (BargainBook bargainBook : resultList) {
			System.out.println(bargainBook.getBookName() + " ddddd");
		}
		return resultList;
	}

	@Override
	public Bargain getBargainByBargainId(int bargainId) {
		Session session = sessionFactory.openSession();
		String hql = "from Bargain where bargainId=" + bargainId;
		Query query = session.createQuery(hql);
		Bargain bargain = (Bargain) query.list().get(0);
		session.close();
		return bargain;
	}

	@Override
	public Bargain getBargainByBookId(int bookId) {
		Session session = sessionFactory.openSession();
		String hql = "from Bargain where bookId=" + bookId
				+ " and bargainStatus=1";
		Query query = session.createQuery(hql);
		Bargain bargain = (Bargain) query.list().get(0);
		session.close();
		return bargain;
	}

}
