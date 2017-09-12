package com.moneyTransfer.dao.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.moneyTransfer.dao.AbstractDAO;
import com.moneyTransfer.dao.AccountDAO;
import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Status;
import com.moneyTransfer.model.Transaction;
import com.moneyTransfer.model.TransactionType;

/**
 * 
 * @author luis.stubbia
 *
 */
@SuppressWarnings("unchecked")
public class AccountDAOImpl extends AbstractDAO<Account> implements AccountDAO {

	private static AccountDAO accountDao = new AccountDAOImpl();
	
	private AccountDAOImpl(){}
	
	@Override
	public Set<Account> getAccountsByUserId(Long id) {
		Session session = getSession();
		Query query = session.createQuery("from Account where user.id = :userId");
		query.setLong("userId", id);
		session.close();
		return new LinkedHashSet<Account>(query.list());
	}
	
	@Override
	public Account getAccountById(Long id, Session session) {
		Query query = session.createQuery("from Account where id = :id");
		query.setLong("id", id);
		return (Account) query.uniqueResult();
	}

	@Override
	public Set<Account> getAccounts() {
		Session session = getSession();
		Query query = session.createQuery("from Account");
		LinkedHashSet<Account> accounts = new LinkedHashSet<Account>(query.list());
		session.close();
		return accounts;
	}

	@Override
	public Account findAccount(Long id) {
		Session session = getSession();
		Query query = session.createQuery("from Account where id = :idAcc");
		query.setLong("idAcc", id);
		Account account = (Account) query.uniqueResult();
		session.close();
		return account;
	}
	
	@Override
	public void deleteById(Long id) {
		Account account = findAccount(id);
		if (account != null) {
			Session session = getSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			session.delete(account);
			tx.commit();
			session.close();
		}
	}

	private Transaction getTransactionById(Long id, Session session) {
		Query query = session.createQuery("from Transaction where id = :id");
		query.setLong("id", id);
		return (Transaction) query.uniqueResult();
	}
	
	@Override
	public void rollbackTransaction(Long id) {
		Session session = getSession();
		Transaction transaction = getTransactionById(id, session);
		if(transaction != null && transaction.getStatus().equals(Status.ACTIVE)){
			Transaction transAux = getTransactionById(transaction.getRef().getId(), session);
			Transaction txAuxTo, txAuxFrom;
			try {
				transAux.setStatus(Status.DELETED);
				transaction.setStatus(Status.DELETED);
				
				txAuxFrom = (Transaction) transaction.clone();
				txAuxTo = (Transaction) transAux.clone();
				
				txAuxFrom.setType(transAux.getType());
				txAuxTo.setType(transaction.getType());
				
				txAuxTo.setName(transaction.getName() + " - ROLL BACK");
				txAuxFrom.setName(transAux.getName() + " - ROLL BACK");
				
				txAuxFrom.setId(null);
				txAuxTo.setId(null);
				
				org.hibernate.Transaction txH = session.beginTransaction();
				session.update(transAux);
				session.update(transaction);
				session.save(txAuxFrom);
				session.save(txAuxTo);
				txH.commit();
				session.close();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public void addTransaction(Long fromId, Long toId, Transaction transaction) {
		Transaction txAux;
		try {
			txAux = (Transaction) transaction.clone();
			
			Session session = getSession();
			Account fromAcc = getAccountById(fromId, session);
			Account toAcc = getAccountById(toId, session);
			
			transaction.setType(TransactionType.DEBIT);
			transaction.setAccountTo(toAcc);
			transaction.setAccount(fromAcc);
			transaction.setName(transaction.toString());
			transaction.setRef(txAux);
			
			txAux.setType(TransactionType.CREDIT);
			txAux.setAccount(toAcc);
			txAux.setAccountTo(fromAcc);
			txAux.setName(txAux.toString());
			txAux.setRef(transaction);
			
			org.hibernate.Transaction txH = session.beginTransaction();
			session.save(transaction);
			session.save(txAux);
			txH.commit();
			session.close();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public static AccountDAO instance() {
		return accountDao;
	}
}