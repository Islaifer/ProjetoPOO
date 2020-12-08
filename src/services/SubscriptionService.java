package services;

import java.util.Date;
import java.util.List;

import daos.SubscriptionDao;
import models.Subscription;
import models.SubscriptionStatus;
import models.User;

public class SubscriptionService {
	
	public SubscriptionService() {
		super();
	}
	
	public List<Subscription> getAll() throws Exception{
		return SubscriptionDao.getAll();
	}
	
	public Subscription getById(int id) throws Exception {
		return SubscriptionDao.getById(id);
	}
	
	public void post(Double amount , User user, SubscriptionStatus status, Date dueDate) throws Exception {
		Subscription subscription = new Subscription(dueDate, status, user, amount );
		SubscriptionDao.insert(subscription);
	}
	
	public void deleteById(int id) throws Exception {
		SubscriptionDao.deleteById(id);
	}
}
