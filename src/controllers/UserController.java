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
	
	public User getByCPF(int cpf) throws Exception {
		return this.userService.getByCPF(cpf);
	}
	
	public void post(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, int idPlan) throws Exception {
		this.userService.post(firstName, lastName, rg, cpf, birthdate, phoneNumber, address, idPlan);
	}
	
	public void deleteById(int id) throws Exception {
		this.userService.deleteById(id);
	}
}
