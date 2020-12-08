package models;

import java.util.Date;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private int rg;
	private int cpf;
	private Date birthdate;
	private int phoneNumber;
	private String address;
	private String addressNumber;
	private String state;
	private String city;
	private Plan plan;
	
	public User(String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, Plan plan) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rg = rg;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setPlan(plan);
	}
	
	public User(int id, String firstName, String lastName, int rg, int cpf, Date birthdate, int phoneNumber, String address, Plan plan) {
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
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public int getCpf() {
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
	public int getPhoneNumber() {
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
	public String getAddressNumber() {
		return addressNumber;
	}
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
}
