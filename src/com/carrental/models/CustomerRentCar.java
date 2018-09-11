package com.carrental.models;

import java.time.LocalDate;

public class CustomerRentCar {

	private Car car;
	private Customer customer;
	private LocalDate rentalDate;
	private int bookingStatus;
	private String updateAt;
	
	

	CustomerRentCar(Customer customer, Car car, LocalDate date, int bookingStatus) {
		this.customer = customer;
		this.car = car;
		this.rentalDate = date;
		this.bookingStatus = bookingStatus;
		
	}
	
	
}
