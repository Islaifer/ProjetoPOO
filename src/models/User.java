package models;

import java.util.Date;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private long rg;
	private long cpf;
	private Date birthdate;
	private long phoneNumber;
	private String address;
	private Plan plan;
	
	public User(String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, Plan plan) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rg = rg;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setPlan(plan);
	}
	
	public User(int id, String firstName, String lastName, long rg, long cpf, Date birthdate, long phoneNumber, String address, Plan plan) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rg = rg;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setPlan(plan);
	}
	
	public User() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}
