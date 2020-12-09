package models;

import java.util.Date;

public class Payment {
	private int id;
	private Date date;
	private User user;
	private double amount;
	private Subscription subscription;

	public Payment(int id,Date date, User user, double price, Subscription subscription) {
		this.date = date;
		this.user = user;
		this.amount = price;
		this.id = id;
	}
	
	public Payment(Date date, User user, double price, Subscription subscription) {
		this.date = date;
		this.user = user;
		this.amount = price;
		this.subscription = subscription;
	}
	
	public Payment() {
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public void setPrice(double amount) {
		this.amount = amount;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}
