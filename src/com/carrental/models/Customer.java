package com.carrental.models;

import java.time.LocalDate;

public class Customer {
	private long customerID;
	private String fName;
	private String lName;
	private LocalDate DOB;
	private String licenseNumber;
	private String universityID;

	Customer(long customerID, String fName, String lName, LocalDate DOB, String licenseNumber, String universityID) {
		this.customerID = customerID;
		this.fName = fName;
		this.lName = lName;
		this.DOB = DOB;
		this.licenseNumber = licenseNumber;
		this.universityID = universityID;
	}
	
	@Override
	public String toString() {
		return String.join("\n", fName + " " + lName,
				"Date of Birth: " + DOB.toString(),
				"License ID: " + licenseNumber);
	}

	public static Customer getDummyUser(String userID) {
		Customer customer = new Customer(0, "Ramesh", "Kumar", LocalDate.of(2000, 1,  1),"1992", "19101910");
		return customer;
	}
	
}
