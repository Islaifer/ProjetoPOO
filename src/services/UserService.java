package services;

import java.util.Date;
import java.util.List;

import daos.UserDao;
import models.Plan;
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
	
	public void post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, String addressNumber, String state, String city, Plan plan) throws Exception {
		User user = new User(0, firstName, lastName, rg, cpf, birthdate, phoneNumber, address, addressNumber, state, city, plan);
		UserDao.insert(user);
	}

	
	public void deleteById(int id) throws Exception {
		UserDao.deleteById(id);
	}
}
