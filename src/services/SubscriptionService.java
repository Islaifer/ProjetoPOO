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
	
	public List<Subscription> filterByStatus(int statusId) throws Exception {
		return SubscriptionDao.filterByStatus(statusId);
	}
	public void post(User user, SubscriptionStatus status, Date dueDate) throws Exception {
		Subscription subscription = new Subscription(dueDate, status, user);
		SubscriptionDao.insert(subscription);
	}
	
	public void deleteById(int id) throws Exception {
		SubscriptionDao.deleteById(id);
	}

}
