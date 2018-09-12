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
				+ "Age : " + Period.between(DOB, LocalDate.now()).getYears() + "\n" + "Gender : " + gender;
		return str;
	}

	public static Customer getDummyUser(String userID) {
		Customer customer = new Customer(0, "Ilia", "Nan", "Male", LocalDate.of(2000, 1, 1), "1992", "19101910");
		return customer;
	}

}
