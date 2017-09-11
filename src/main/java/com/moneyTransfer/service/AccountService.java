package com.moneyTransfer.service;

import java.util.Set;

import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Transaction;
import com.moneyTransfer.model.User;

public interface AccountService {
	
	/**
	 * Add or update account
	 * @param account
	 */
	public void addOrUpdateAccount(Account account);

	/**
	 * Delete account
	 * @param account
	 */
	public void deleteAccount(Account account);
	
	/**
	 * Get account by user
	 * @param user
	 * @return
	 */
	public Set<Account> getAccountsByUser(User user);
	
	/**
	 * Get account list
	 * @return
	 */
	public Set<Account> getAccounts();
	
	/**
	 * Add transaction
	 * @param transaction
	 */
	public void addTransaction(Transaction transaction);
	
	/**
	 * delete transaction
	 * @param transaction
	 */
	public void deleteTransaction(Transaction transaction);
}
