package com.javaeetest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.OrdersDao;
import com.javaeetest.entity.Orders;

public class OrdersDaoImpl extends BaseDaoImpl<Orders> implements OrdersDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getAllOrdersForManager() {
		List<Orders> list = null;
		Session session = sessionFactory.openSession();
		String hql = "from Orders order by orderId desc";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Orders> getOrdersForManagerForStatus(int status) {
		List<Orders> list = null;
		Session session = sessionFactory.openSession();
		String hql = "from Orders where orderStatus=" + status
				+ " order by orderId desc";
		Query query = session.createQuery(hql);
		list = query.list();
		session.close();
		return list;
	}

	/**
	 * status=1代表处理成功，status=-1代表处理失败
	 */
	@Override
	public int updateOrdersStatus(int orderId, int status) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			tx = session.beginTransaction();
			String hql = "update Orders set orderStatus=" + status
					+ " where orderId=" + orderId;
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

	@Override
	public List<Orders> queryByPage(int offset, int pageSize, int status) {

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Orders> list = null;
		String hql;
		try {
			if (status == 2) {
				hql = "from Orders order by orderId asc";
			} else {
				hql = "from Orders where orderStatus=" + status
						+ " order by orderId asc";
			}
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
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
	public int getAllRowCount(int status) {
		Session session = sessionFactory.openSession();
		String hql;
		if (status == 2) {
			hql = "from Orders";
		} else {
			hql = "from Orders where orderStatus=" + status;
		}
		Query query = session.createQuery(hql);
		int allRows = query.list().size();
		session.close();
		return allRows;
	}

	public Orders findOrders(int id) {
		String hql = "select * from tb_orders as orders where orders.orderId=?0";
		List<Orders> order = find(hql, id);
		System.out.println("sfsdfds" + order.size());
		if (order != null && order.size() > 0) {

			System.out.println(order.get(0).getOrderId() + " "
					+ order.get(0).getAddressId() + "ccc");
			return order.get(0);

		}

		return null;

	}

}
