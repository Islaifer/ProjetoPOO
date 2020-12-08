package services;

import java.util.Date;
import java.util.List;

import daos.UserDao;
import models.User;

public class UserService {
	
	public UserService() {
	
	}
	public List<User> getAll() throws Exception{
		return UserDao.get();
	}
	
	public User getById(int id) throws Exception {
		return UserDao.getById(id);
	}
	
	public User post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, String addressNumber, String state, String city, int planId) throws Exception {
		User user = new User(0, firstName, lastName, rg, cpf, birthdate, phoneNumber, address, addressNumber, state, city, planId);
		UserDao.insert(user);
		return user;
	}
	
	public List<User> post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, String addressNumber, String state, String city, int planId, List<User> list) throws Exception {
		User user = new User(0, firstName, lastName, rg, cpf, birthdate, phoneNumber, address, addressNumber, state, city, planId);
		UserDao.insert(user);
		list.add(user);
		return list;
	}
	
	public void deleteById(int id) throws Exception {
		UserDao.deleteById(id);
	}
}
