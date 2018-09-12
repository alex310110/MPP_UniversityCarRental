package com.carrental.models;


public class Car {

	private long carID;
	private String registerationNumber;

	private String carBrand;
	private String color;
	private int seats;
	private int luggage;
	private int rentalFees;

	Car(long carID, String carBrand,String registerationNumber, String color, int seats, int luggage, int rentalFees) {
		this.carID = carID;
		this.registerationNumber = registerationNumber;
		this.color = color;
		this.seats = seats;
		this.luggage = luggage;
		this.rentalFees = rentalFees;
		this.carBrand = carBrand;
	}
	public long getCarID() {
		return carID;
	}

	public String getRegisterationNumber() {
		return registerationNumber;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public String getColor() {
		return color;
	}

	public int getSeats() {
		return seats;
	}

	public int getLuggage() {
		return luggage;
	}

	public int getRentalFees() {
		return rentalFees;
	}

	@Override
	public String toString() {
		String str = "";
		str = "Car Brand : "+carBrand+"\n"
				+"Registeration Number : "+registerationNumber+"\n"
				+"Color : "+color+"\n"
				+"No of seats: "+seats+"\n"
				+"Luggage Capacity : "+luggage+"\n"
				+"Rental Fees : "+rentalFees;
		return str;
	}
	
}
