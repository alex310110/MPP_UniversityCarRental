package com.carrental.models;


public class Car {

	private long carID;
	private String registerationNumber;

	private String carBrand;
	private String color;
	private int seats;
	private float lugguage;
	private float rentalFees;

	Car(long carID, String carBrand,String registerationNumber, String color, int seats, float lugguage, float rentalFees) {
		this.carID = carID;
		this.registerationNumber = registerationNumber;
		this.color = color;
		this.seats = seats;
		this.lugguage = lugguage;
		this.rentalFees = rentalFees;
		this.carBrand = carBrand;
	}
	
	@Override
	public String toString() {
		String str = "";
		str = "Car Brand : "+carBrand+"\n"
				+"Registeration Number : "+registerationNumber+"\n"
				+"Color : "+color+"\n"
				+"No of seats: "+seats+"\n"
				+"Luggage Capacity : "+lugguage+"\n"
				+"Rental Fees : "+rentalFees;
		return str;
	}
	
}
