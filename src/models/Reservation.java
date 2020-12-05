package models;

import java.util.Date;

public class Reservation {
	private User user;
	private String username;
	private Date date;
	private String space;
	private int peopleqnt;
	
	public Reservation(User user, Date date, String space, int peopleqnt) {
		this.user = user;
		this.date = date;
		this.space = space;
		this.peopleqnt = peopleqnt;
		this.username = user.getFirstName() + " " + user.getLastName();
	}
	
	public Reservation() {
		super();
	}
	
	

	public String getUsername() {
		return username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public int getPeopleqnt() {
		return peopleqnt;
	}

	public void setPeopleqnt(int peopleqnt) {
		this.peopleqnt = peopleqnt;
	}
	
	
}
