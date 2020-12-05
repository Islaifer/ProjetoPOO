package models;

import java.util.Date;

public class Payment {
	private int id;
	private Date date;
	private User user;
	private double price;

	public Payment(int id,Date date, User user, double price) {
		this.date = date;
		this.user = user;
		this.price = price;
		this.id = id;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
