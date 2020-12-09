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
	
	public User getByCPF(long cpf) throws Exception {
		return this.userService.getByCPF(cpf);
	}
	
	public void post(String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, int idPlan) throws Exception {
		this.userService.post(firstName, lastName, rg, cpf, birthdate, phoneNumber, address, idPlan);
	}
	
	public void deleteById(int id) throws Exception {
		this.userService.deleteById(id);
	}
	
	public void edit(int id, String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, int idPlan) throws Exception {
		this.userService.edit(id, firstName, lastName, rg, cpf, birthdate, phoneNumber, address, idPlan);
	}
}
