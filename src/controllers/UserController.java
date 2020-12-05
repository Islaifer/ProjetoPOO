package controllers;

import java.util.List;

import daos.UserDao;
import models.User;

public class UserController {
	
	public UserController() {
		super();
	}
	
	public List<User> getAll() throws Exception{
		return UserDao.get();
	}
	
	public User getById(int id) throws Exception {
		return UserDao.getById(id);
	}
	
	public User post(User user) throws Exception {
		UserDao.insert(user);
		return user;
	}
	
	public List<User> post(User user, List<User> list) throws Exception {
		UserDao.insert(user);
		list.add(user);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		UserDao.deleteById(id);
	}
}
