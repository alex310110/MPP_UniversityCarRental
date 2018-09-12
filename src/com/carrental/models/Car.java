package com.carrental.models;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Car {

	private long carID;
	private String registerationNumber;
	private String carBrand;
	private String color;
	private int rentalFees;

	Car(long carID, String carBrand, String registerationNumber, String color, int rentalFees) {
		this.carID = carID;
		this.registerationNumber = registerationNumber;
		this.color = color;
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

	public int getRentalFees() {
		return rentalFees;
	}

	@Override
	public String toString() {
		String str = "";
		str =  color +" "+carBrand +" "+getType() +" $"+rentalFees ;
		return str;
	}
	
	public String getFormattedDetail() {
		return String.join("\n", getColor() + " " + getCarBrand(),
				"License Plate: " + getRegisterationNumber(),
				getType() + ": " +
				getSeats() + " seats / " + getLuggage() + " luggages",
				"$" + getRentalFees());
	}

	public abstract int getLuggage();

	public abstract int getSeats();

	public abstract String getType();

	public static ArrayList<Car> getTempCarsList() {

		ArrayList<Car> list = new ArrayList<Car>();
		list.add(new Compact(1, "Toyota", "11A", "Black", 50));
		list.add(new SUV(1, "Suzuki", "192AAS", "White", 100));
		list.add(new Compact(1, "Nissan", "ACC11", "Golden", 40));

		return list;

	}

}
