package com.tender.models;

public class bids {
	 private int tid;
	 private String property;
	 private String location;
	 private int basePrice;
	 private int vid;
	 private String vName;
	 private String email;
	 private String password;
	 private String company;
	private int offer;
	private String bidStatus;
	 public bids(int tid, String property, String location, int basePrice, int vid, String vName, String email,
			String password, String company, int offer, String bidStatus) {
		super();
		this.tid = tid;
		this.property = property;
		this.location = location;
		this.basePrice = basePrice;
		this.vid = vid;
		this.vName = vName;
		this.email = email;
		this.password = password;
		this.company = company;
		this.offer = offer;
		this.bidStatus = bidStatus;
	}

	public bids() {
		// TODO Auto-generated constructor stub
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
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
		return "bids [tid=" + tid + ", property=" + property + ", location=" + location + ", basePrice=" + basePrice
				+ ", vid=" + vid + ", vName=" + vName + ", email=" + email + ", password=" + password + ", company="
				+ company + ", offer=" + offer + ", bidStatus=" + bidStatus + "]";
	}
	 
	
}
