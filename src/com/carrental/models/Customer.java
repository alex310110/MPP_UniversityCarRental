package com.carrental.models;

import java.time.LocalDate;
import java.time.Period;

public class Customer {
	private long customerID;
	private String fName;
	private String lName;

	private String gender;

	private LocalDate DOB;
	private String licenseNumber;
	private String universityID;
	public long getCustomerID(){return customerID;}
	
	Customer(long customerID, String fName, String lName, String gender, LocalDate DOB, String licenseNumber,
			String universityID) {
		this.customerID = customerID;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.DOB = DOB;
		this.licenseNumber = licenseNumber;
		this.universityID = universityID;
	}

	@Override
	public String toString() {
		String str = "";
		str = "ID : " + universityID + "\n" + "Name : " + fName + " " + lName + "\n"
				+ "Age : " + Period.between(DOB, LocalDate.now()).getYears()
				+ " Gender : " + gender + "\n" +
				"License Number: " + licenseNumber;
		return str;
	}
}
