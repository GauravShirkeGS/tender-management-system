package com.tender.models;

public class tender {
 private int tid;
 private String property;
 private String location;
 private String StratDate;
 private String EndDate;
 private int basePrice;
 
 public tender() {
	
}

public tender(int tid, String property, String location, String stratDate, String endDate, int basePrice) {
	super();
	this.tid = tid;
	this.property = property;
	this.location = location;
	StratDate = stratDate;
	EndDate = endDate;
	this.basePrice = basePrice;
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

public String getStratDate() {
	return StratDate;
}

public void setStratDate(String stratDate) {
	StratDate = stratDate;
}

public String getEndDate() {
	return EndDate;
}

public void setEndDate(String endDate) {
	EndDate = endDate;
}

public int getBasePrice() {
	return basePrice;
}

public void setBasePrice(int basePrice) {
	this.basePrice = basePrice;
}

@Override
public String toString() {
	return "tender [tid=" + tid + ", property=" + property + ", location=" + location + ", StratDate=" + StratDate
			+ ", EndDate=" + EndDate + ", basePrice=" + basePrice + "]";
}



}
