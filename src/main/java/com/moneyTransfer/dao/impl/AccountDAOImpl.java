package com.moneyTransfer.dao.impl;


import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.moneyTransfer.dao.AbstractDAO;
import com.moneyTransfer.dao.AccountDAO;
import com.moneyTransfer.model.Account;

/**
 * 
 * @author luis.stubbia
 *
 */
@SuppressWarnings("unchecked")
public class AccountDAOImpl extends AbstractDAO<Account> implements AccountDAO {

	public Set<Account> getAccountsByUserId(Long id){
		Session session = getSession();
		Query query = session.createQuery("from Account where user.id = :userId");
		query.setLong("userId", id);
		session.close();
		return new LinkedHashSet<Account>(query.list());
	}

	@Override
	public Set<Account> getAccounts() {
		Session session = getSession();
		Query query = session.createQuery("from Account");
		LinkedHashSet<Account> accounts = new LinkedHashSet<Account>(query.list());
		session.close();
		return accounts;
	}
}