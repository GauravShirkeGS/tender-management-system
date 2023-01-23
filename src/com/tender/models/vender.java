package com.tender.models;

public class vender {
	 private int vid;
	 private String vName;
	 private String email;
	 private String password;
	 private String company;
	
	 public vender() {
		// TODO Auto-generated constructor stub
	}

	public vender(int vid, String vName, String email, String password, String company) {
		super();
		this.vid = vid;
		this.vName = vName;
		this.email = email;
		this.password = password;
		this.company = company;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "vender [vid=" + vid + ", vName=" + vName + ", email=" + email + ", password=" + password + ", company="
				+ company + "]";
	}
	 
	 
	 
}
