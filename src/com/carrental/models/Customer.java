package com.carrental.models;

import java.time.LocalDate;

public class Customer {
	private long customerID;
	private String fName;
	private String lName;
	private String SSN;
	private LocalDate DOB;
	private String licenseNumber;
	private String universityID;
	

	Customer(long customerID, String fName, String lName, String SSN, LocalDate DOB, String licenseNumber, String universityID) {
		this.customerID = customerID;
		this.fName = fName;
		this.lName = lName;
		this.SSN = SSN;
		this.DOB = DOB;
		this.licenseNumber = licenseNumber;
		this.universityID = universityID;
	}
	
	@Override
	public String toString() {
		String str = "";
		str = "ID : "+universityID+"\n"
				+"First Name : "+fName+"\n"
				+"Last Name : "+lName+"\n"
				+"Date of Birth: "+DOB.getDayOfMonth()+"/"+DOB.getDayOfMonth()+"/"+DOB.getYear()+"\n"
				+"License : "+licenseNumber+"\n"
				+"SSN : "+SSN;
		return str;
	}

	public static Customer getDummyUser(String userID) {
		Customer customer = new Customer(0, "Ramesh", "Kumar", "110", LocalDate.of(2000, 1,  1),"1992", "19101910");
		return customer;
	}
	
}
