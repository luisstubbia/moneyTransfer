package com.moneyTransfer.service;

import java.util.Set;

import com.moneyTransfer.model.User;

public interface UserService {

	/**
	 * Add or update user
	 * 
	 * @param account
	 */
	public void addOrUpdateUser(User user);

	/**
	 * Delete user by id.
	 * 
	 * @param id
	 */
	void deleteUser(Long id);

	/**
	 * Delete user
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * Get user list
	 * 
	 * @return
	 */
	public Set<User> getUsers();

	/**
	 * Find user by id
	 * 
	 * @param user
	 */
	public User findUser(Long id);
}
