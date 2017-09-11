package com.moneyTransfer.service.impl;

import java.util.Set;

import com.moneyTransfer.dao.UserDAO;
import com.moneyTransfer.dao.impl.UserDAOImpl;
import com.moneyTransfer.model.User;
import com.moneyTransfer.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDao;
	
	public UserServiceImpl(){
		userDao = new UserDAOImpl();
	}

	@Override
	public void addOrUpdateUser(User user) {
		if(user != null){
			if(user.getId() != null){
				userDao.update(user);
			} else {
				userDao.add(user);
			}
		}
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteById(id);
	}
	
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public Set<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User findUser(Long id) {
		return userDao.findUser(id);
	}
}
