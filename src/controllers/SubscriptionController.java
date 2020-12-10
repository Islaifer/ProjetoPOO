package controllers;

import java.util.List;

import models.Subscription;
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
	
	public List<Subscription> filterByStatus(int statusId) throws Exception {
		return this.subscriptionService.filterByStatus(statusId);
	}
	
	public void post() throws Exception {
		this.subscriptionService.post();
	}
	
	public void deleteById(int id) throws Exception {
		this.subscriptionService.deleteById(id);
	}
}
