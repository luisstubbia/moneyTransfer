package com.moneyTransfer.service;

import java.util.Set;

import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Transaction;
import com.moneyTransfer.model.User;

public interface AccountService {

	/**
	 * Add or update account
	 * 
	 * @param account
	 */
	public void addOrUpdateAccount(Account account);

	/**
	 * Delete account
	 * 
	 * @param account
	 */
	public void deleteAccount(Account account);

	/**
	 * Get account by user
	 * 
	 * @param user
	 * @return
	 */
	public Set<Account> getAccountsByUser(User user);

	/**
	 * Get account list
	 * 
	 * @return
	 */
	public Set<Account> getAccounts();

	/**
	 * Add transaction
	 * @param toId 
	 * @param fromId 
	 * 
	 * @param transaction
	 * @return 
	 */
	public void addTransaction(Long fromId, Long toId, Transaction transaction);

	/**
	 * find account
	 * @param id
	 * @return account
	 */
	public Account findAccount(Long id);

	/**
	 * Delete account by id
	 * @param id
	 */
	void deleteAccount(Long id);

	/**
	 * Delete transaction by id
	 * @param Id
	 */
	void deleteTransaction(Long Id);
}
