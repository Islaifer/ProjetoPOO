package models;

import java.util.Date;

public class Subscription {
	private int id;
	private Date dueDate;
	private SubscriptionStatus status;
	private User user;
	private double amount;
	
	public Subscription(int id, Date dueDate, SubscriptionStatus status, User user, double amount) {
		this.id = id;
		this.dueDate = dueDate;
		this.status = status;
		this.user = user;
		this.amount = amount;
	}
	
	public Subscription(Date dueDate, SubscriptionStatus status, User user, double amount) {
		this.dueDate = dueDate;
		this.status = status;
		this.user = user;
		this.amount = amount;
	}
	
	public Subscription() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public SubscriptionStatus  getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
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

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
