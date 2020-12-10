package services;

import java.util.Date;
import java.util.List;

import daos.PlanDao;
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
	
	public User getByCPF(long cpf) throws Exception {
		return UserDao.getByCPF(cpf);
	}
	
	public void post(String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, int idPlan) throws Exception {
		Plan plan = PlanDao.getById(idPlan);
		User user = new User(firstName, lastName, rg, cpf, birthdate, phoneNumber, address, plan);
		UserDao.insert(user);
	}

	
	public void deleteById(int id) throws Exception {
		UserDao.deleteById(id);
	}
	
	public void edit(int id,String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, int idPlan) throws Exception {
		Plan plan = PlanDao.getById(idPlan);
		User user = new User(id, firstName, lastName, rg, cpf, birthdate, phoneNumber, address, plan);
		UserDao.update(user);
	}
}
