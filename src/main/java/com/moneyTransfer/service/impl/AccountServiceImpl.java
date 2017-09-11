package com.moneyTransfer.service.impl;

import java.util.Set;

import com.moneyTransfer.dao.AccountDAO;
import com.moneyTransfer.dao.impl.AccountDAOImpl;
import com.moneyTransfer.model.Account;
import com.moneyTransfer.model.Transaction;
import com.moneyTransfer.model.User;
import com.moneyTransfer.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDao;

	public AccountServiceImpl() {
		accountDao = new AccountDAOImpl();
	}

	@Override
	public void addOrUpdateAccount(Account account) {
		if (account != null) {
			if (account.getId() != null) {
				accountDao.update(account);
			} else {
				accountDao.add(account);
			}
		}
	}

	@Override
	public void deleteAccount(Account account) {
		accountDao.delete(account);
	}

	@Override
	public Set<Account> getAccountsByUser(User user) {
		return accountDao.getAccountsByUserId(user.getId());
	}

	@Override
	public Set<Account> getAccounts() {
		return accountDao.getAccounts();
	}

	@Override
	public void addTransaction(Transaction transaction) {

	}

	@Override
	public void deleteTransaction(Transaction transaction) {

	}
}
