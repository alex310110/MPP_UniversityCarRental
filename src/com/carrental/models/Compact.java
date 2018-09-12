package com.carrental.models;

public class Compact extends Car {

	Compact(long carID, String carBrand, String registerationNumber, String color, int rentalFees) {
		super(carID, carBrand, registerationNumber, color, rentalFees);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLuggage() {

		return 2;
	}

	@Override
	public int getSeats() {
		return 2;
	}

	@Override
	public String getType() {
		return "Compact";
	}

}
