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
	private Space space;
	private int peopleqnt;
	
	public Reservation(int id, User user, Date date, Space space, int peopleqnt) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hf = new SimpleDateFormat("HH:mm");
		this.id = id;
		this.user = user;
		this.date = date;
		this.space = space;
		this.peopleqnt = peopleqnt;
		this.username = user.getFirstName() + " " + user.getLastName();
		this.datef = df.format(this.date);
		this.hour = hf.format(this.date);
	}
	
	public Reservation() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public int getPeopleqnt() {
		return peopleqnt;
	}

	public void setPeopleqnt(int peopleqnt) {
		this.peopleqnt = peopleqnt;
	}
	
	
}
