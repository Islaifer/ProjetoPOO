package controllers;

import java.util.Date;
import java.util.List;

import models.Subscription;
import models.SubscriptionStatus;
import models.User;
import services.SubscriptionService;


public class SubscriptionController {
	private SubscriptionService subscriptionService;
	
	public SubscriptionController() {
		this.subscriptionService = new SubscriptionService();
	}
	
	public List<Subscription> getAll() throws Exception{
		return this.subscriptionService.getAll();
	}
	
	public Subscription getById(int id) throws Exception {
		return this.subscriptionService.getById(id);
	}
	
	public void post(Double amount , User user, SubscriptionStatus status, Date dueDate) throws Exception {
		this.subscriptionService.post(amount, user, status, dueDate);
	}
	
	public void deleteById(int id) throws Exception {
		this.subscriptionService.deleteById(id);
	}
}
