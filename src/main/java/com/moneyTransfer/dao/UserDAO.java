package com.moneyTransfer.dao;

import java.util.Set;

import com.moneyTransfer.model.User;

/**
 * User data access object.
 * 
 * @author luis.stubbia
 *
 */
public interface UserDAO {
	
	/**
	 * Get list of users by user id.
	 * @param id
	 * @return user
	 */
	public User findUser(Long id);

	/**
	 * Add user
	 * @param user
	 */
	public void add(User user);
	
	/**
	 * Update user
	 * @param user
	 */
	public User update(User user);
	
	/**
	 * Delete user by id.
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * Delete user
	 * @param user
	 */
	public void delete(User user);

	/**
	 * Get users
	 * @return
	 */
	public Set<User> getUsers();
}
