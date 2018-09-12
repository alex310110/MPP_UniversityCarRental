package com.carrental.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerRentCar {

	private Car car;
	private Customer customer;
	private LocalDate rentalDate;
	private int bookingStatus;

	private String updateAt;

	public static int BOOKING_CANCELED = -1;
	public static int BOOKING_BOOKED = 1;
	public static int BOOKING_RETURNED = 0;
	
	DateTimeFormatter dateFormatter;

	CustomerRentCar(Customer customer, Car car, LocalDate date, int bookingStatus) {
		this.customer = customer;
		this.car = car;
		this.rentalDate = date;
		this.bookingStatus = bookingStatus;
		this.dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	}


	public int getBookingStatus() {
		return bookingStatus;
	}
	
	public static ArrayList<CustomerRentCar> getDummyOrders(Customer customer) {
		ArrayList<CustomerRentCar> list = new ArrayList<CustomerRentCar>();
		list.add(new CustomerRentCar(customer,
				new Compact(1, "Toyota", "11A", "Black", 50),
				LocalDate.of(2018, 7, 1), BOOKING_RETURNED));
		list.add(new CustomerRentCar(customer,
				new SUV(1, "Suzuki", "192AAS", "White", 100),
				LocalDate.of(2018, 7, 7), BOOKING_RETURNED));
		list.add(new CustomerRentCar(customer,
				new Compact(1, "Nissan", "ACC11", "Golden", 40),
				LocalDate.of(2018, 7, 9), BOOKING_RETURNED));
		list.add(new CustomerRentCar(customer,
				new SUV(1, "Honda", "JES1124", "Black", 1000),
				LocalDate.of(2018, 7, 11), BOOKING_RETURNED));
		return list;
	}

	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String str = ""+rentalDate.format(formatter)+"\t"+car.getCarBrand();
		return str;
	}

	
	
	
	public String getFormattedDetail() {
		return String.join("\n", rentalDate.format(dateFormatter),
				car.getColor() + " " + car.getCarBrand(),
				"License Plate: " + car.getRegisterationNumber(),
				car.getType()+": " + car.getSeats() + " seats / " +
				car.getLuggage() + " luggages",
				"$" + car.getRentalFees());
	}
}
