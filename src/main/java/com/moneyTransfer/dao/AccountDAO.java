package com.moneyTransfer.dao;

import java.util.Set;

import org.hibernate.Session;

import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Transaction;

/**
 * Account data access object.
 * 
 * @author luis.stubbia
 *
 */
public interface AccountDAO {

	/**
	 * Get list of accounts by user id.
	 * 
	 * @param id
	 * @return
	 */
	public Set<Account> getAccountsByUserId(Long id);

	/**
	 * Add account
	 * 
	 * @param account
	 */
	public void add(Account account);

	/**
	 * Update account
	 * 
	 * @param account
	 */
	public Account update(Account account);

	/**
	 * Delete account
	 * 
	 * @param account
	 */
	public void delete(Account account);

	/**
	 * Get accounts
	 * 
	 * @return
	 */
	public Set<Account> getAccounts();

	/**
	 * Find account
	 * @param id
	 * @return
	 */
	public Account findAccount(Long id);

	/**
	 * delete
	 * @param id
	 */
	void deleteById(Long id);

	/**
	 * get account by id
	 * @param id
	 * @param session
	 * @return
	 */
	Account getAccountById(Long id, Session session);

	public void rollbackTransaction(Long id);

	public void addTransaction(Long fromId, Long toId, Transaction transaction);
}
