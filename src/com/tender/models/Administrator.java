package com.tender.models;

import java.time.LocalDate;

public class Administrator {

	private String Name;
	private String userName;
	private String password ;
	
	public Administrator() {
		// TODO Auto-generated constructor stub
	}

	public Administrator(String name, String userName, String password) {
		super();
		Name = name;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [Name=" + Name + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
