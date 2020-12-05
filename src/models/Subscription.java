package models;

import java.util.Date;

public class Subscription {
	private int id;
	private Date valid;
	private String status;
	private User user;
	private String username;
	private String userRg;
	private double valor;
	
	public Subscription(int id, Date valid, String status, User user, double valor) {
		this.id = id;
		this.valid = valid;
		this.status = status;
		this.user = user;
		this.valor = valor;
		this.username = this.user.getFirstName() + " " + this.user.getLastName();
		this.userRg = "" + this.user.getRg();
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getValid() {
		return valid;
	}

	public void setValid(Date valid) {
		this.valid = valid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getUsername() {
		return username;
	}

	public String getUserRg() {
		return userRg;
	}
}
