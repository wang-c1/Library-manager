package com.javaeetest.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javaeetest.common.dao.impl.BaseDaoImpl;
import com.javaeetest.dao.UserDao;
import com.javaeetest.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findAllUser() {
		List<User> resultList = find("from User order by userId asc");
		return resultList;
	}

	/**
	 * 按页查询用户的信息
	 * 
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryByPage(int offset, int pageSize) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> list = null;
		try {
			String hql = "from User";
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			if (query.list().size() != 0) {
				query.setFirstResult(offset);
				query.setMaxResults(pageSize);
			}
			
			if(query != null){
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

	/**
	 * 得到所有用户的行数
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public int getAllRowCount() {
		String hql = "from User";
		int allRows = find(hql).size();
		return allRows;
	}

	@Override
	public User findUserByUserId(int userId) {
		Session session = sessionFactory.openSession();
		String hql = "from User where userId=" + userId;
		Query query = session.createQuery(hql);
		
		User user = (User) query.list().get(0);
		return user;
	}
	
	@Override
	public int deleteUser(int userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int ret = 0;
		try {
			String hql = "delete User where userId = "
					+ userId;
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			ret = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("UserDao中删除用户的状态出现异常");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret;
	}

}
