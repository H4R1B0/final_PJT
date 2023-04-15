package com.traveler.dto;

public class User {
	private String id;
	private String pwd;
	private String email;
	private boolean advertisement;
	public User(String id, String pwd, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.advertisement = false;
	}
	public User(String id, String pwd, String email, boolean advertisement) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.advertisement = advertisement;
	}
	
	public User() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(boolean advertisement) {
		this.advertisement = advertisement;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", email=" + email + ", advertisement=" + advertisement + "]";
	}

	
	

}
