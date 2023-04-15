package com.traveler.model.service;

import java.sql.SQLException;

import com.traveler.dto.User;
import com.traveler.model.dao.UserDao;


public class UserService {
	private UserDao userDao = UserDao.getInstance();
	private static UserService userService = new UserService();

	private UserService() {}
	
	static public UserService getInstance() {
		return userService;
	}
	
	public String login(User user) throws SQLException {
		return userDao.login(user);
	}
	
	public boolean signin(User user) throws SQLException {
		return userDao.signin(user);
	}
	
	public User check(User user) throws SQLException {
		return userDao.check(user);
	}
	
	public boolean update(User user) throws SQLException {
		return userDao.update(user);
	}
	
	public boolean delete(User user) throws SQLException {
		return userDao.delete(user);
	}
	
}
