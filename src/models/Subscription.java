package models;

import java.util.Date;

public class Subscription {
	private int id;
	private Date dueDate;
	private SubscriptionStatus status;
	private String username;
	private String statusName;
	private String cpf;
	private User user;
	private double amount;
	
	public Subscription(int id, Date dueDate, SubscriptionStatus status, User user, double amount) {
		this.id = id;
		this.dueDate = dueDate;
		this.status = status;
		this.user = user;
		this.amount = amount;
		this.username = this.user.getFirstName() + " " + this.user.getLastName();
		this.statusName = this.status.getName();
		this.cpf = this.user.getCpf() + "";
	}
	
	public String getCpf() {
		String aux = cpf;
		char [] vet = aux.toCharArray();	
		if(vet.length == 11) {
			return aux.substring(0, 3) + "." + aux.substring(3, 6) + "." + aux.substring(6, 9) + "-" + aux.substring(9, 11);
		}else {
			return 0 + aux.substring(0, 2) + "." + aux.substring(2, 5) + "." + aux.substring(5, 8) + "-" + aux.substring(8, 10);
		}
	}



	public String getUsername() {
		return username;
	}



	public String getStatusName() {
		return statusName;
	}



	public Subscription(SubscriptionStatus status, User user) {
		this.status = status;
		this.user = user;
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
