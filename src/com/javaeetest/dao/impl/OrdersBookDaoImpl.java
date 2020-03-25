package com.javaeetest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.OrdersBookDao;
import com.javaeetest.entity.OrdersBook;

public class OrdersBookDaoImpl extends BaseDaoImpl<OrdersBook> implements OrdersBookDao {


	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBook> getOrdersBookByOrderId(int orderId) {
		List<OrdersBook> list = null;
		Session session = sessionFactory.openSession();
		try {
			String hql = "from OrdersBook where order.orderId="+orderId;
			Query query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}

}
