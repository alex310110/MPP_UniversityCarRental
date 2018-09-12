package com.carrental.models;

public class SUV extends Car{

	SUV(long carID, String carBrand, String registerationNumber, String color, int rentalFees) {
		super(carID, carBrand, registerationNumber, color, rentalFees);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLuggage() {

		return 4;
	}

	@Override
	public int getSeats() {
		return 5;
	}

	@Override
	public String getType() {
		return "SUV";
	}

}
