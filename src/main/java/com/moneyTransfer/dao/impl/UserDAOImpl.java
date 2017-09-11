package com.moneyTransfer.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.moneyTransfer.dao.AbstractDAO;
import com.moneyTransfer.dao.UserDAO;
import com.moneyTransfer.model.User;

/**
 * 
 * @author luis.stubbia
 *
 */
@SuppressWarnings("unchecked")
public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	@Override
	public User findUser(Long id) {
		Session session = getSession();
		Query query = session.createQuery("from User where id = :idUser");
		query.setLong("idUser", id);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public Set<User> getUsers() {
		Session session = getSession();
		Query query = session.createQuery("from User");
		LinkedHashSet<User> users = new LinkedHashSet<User>(query.list());
		session.close();
		return users;
	}

	@Override
	public void deleteById(Long id) {
		User user = findUser(id);
		if (user != null) {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			session.close();
		}
	}
}