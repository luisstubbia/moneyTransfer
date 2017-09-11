package com.moneyTransfer.dao;

import java.util.Set;

import com.moneyTransfer.model.Account;

/**
 * Account data access object.
 * 
 * @author luis.stubbia
 *
 */
public interface AccountDAO {
	
	/**
	 * Get list of accounts by user id.
	 * @param id
	 * @return
	 */
	public Set<Account> getAccountsByUserId(Long id);

	/**
	 * Add account
	 * @param account
	 */
	public void add(Account account);
	
	/**
	 * Update account
	 * @param account
	 */
	public Account update(Account account);
	
	/**
	 * Delete account
	 * @param account
	 */
	public void delete(Account account);

	/**
	 * Get accounts
	 * @return
	 */
	public Set<Account> getAccounts();
}
