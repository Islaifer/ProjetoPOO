package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	private int id;
	private User user;
	private String username;
	private Date date;
	private String datef;
	private String hour;
	private String space;
	private int peopleqnt;
	
	@SuppressWarnings("deprecation")
	public Reservation(int id, User user, Date date, String space, int peopleqnt) {
		SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		this.id = id;
		this.user = user;
		this.date = date;
		this.space = space;
		this.peopleqnt = peopleqnt;
		this.username = user.getFirstName() + " " + user.getLastName();
		this.datef = df.format(this.date);
		this.hour = this.date.getHours() + ":" + this.date.getMinutes();
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Reservation() {
		super();
	}
	
	

	public String getDatef() {
		return datef;
	}

	public String getHour() {
		return hour;
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
