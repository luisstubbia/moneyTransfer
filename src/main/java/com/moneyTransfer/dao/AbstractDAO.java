package com.moneyTransfer.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * @author luis.stubbia
 *
 */
public abstract class AbstractDAO<T> {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				Configuration configuration = new Configuration().configure("hibernate.xml");
				StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
				serviceRegistryBuilder.applySettings(configuration.getProperties());
				ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Get hibernate session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Close session factory
	 */
	protected void closeSessionFaccotry() {
		sessionFactory.close();
	}

	/**
	 * Add entity
	 * 
	 * @param t
	 */
	public void add(T entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
	}

	/**
	 * Delete entity
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	/**
	 * Update entity
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
		return entity;
	}
}