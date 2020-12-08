package controllers;

import java.util.Date;
import java.util.List;

import models.User;
import services.UserService;

public class UserController {
	private UserService userService;
	public UserController() {
		this.userService = new UserService();
	}
	
	public List<User> getAll() throws Exception{
		return this.userService.getAll();
	}
	
	public User getById(int id) throws Exception {
		return this.userService.getById(id);
	}
	
	public User post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, String addressNumber, String state, String city, int planId) throws Exception {
		return this.userService.post(firstName, lastName, rg, cpf, birthdate, phoneNumber, address, addressNumber, state, city, planId);
	}
	
	public List<User> post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, String addressNumber, String state, String city, int planId, List<User> list) throws Exception {
		return this.userService.post(firstName, lastName, rg, cpf, birthdate, phoneNumber, address, addressNumber, state, city, planId, list);
	}
	
	public void deleteById(int id) throws Exception {
		this.userService.deleteById(id);
	}
}
