package com.carrental.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.carrental.utils.CustomException;

public class CustomerRentCar {

	private long CRC_ID;
	private Car car;
	private Customer customer;
	private LocalDate rentalDate;
	private int bookingStatus;
	public Car getCar(){return car;}
	public Customer getCustomer(){return customer;}
	public long getCRC_ID(){return CRC_ID;}

	public static final int BOOKING_CANCELED = -1;
	public static final int BOOKING_BOOKED = 1;
	public static final int BOOKING_RETURNED = 0;
	
	DateTimeFormatter dateFormatter;
	public static CustomerRentCar setCustomerRentCar(Customer cus,Car car,LocalDate bookingDate,int bookingStatus,long ID)
	{
		return new CustomerRentCar(cus,car,bookingDate,bookingStatus,ID);
	}
	private CustomerRentCar(Customer customer, Car car, LocalDate date, int bookingStatus,long ID) {
		this.customer = customer;
		this.car = car;
		this.rentalDate = date;
		this.bookingStatus = bookingStatus;
		this.dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		this.CRC_ID = ID;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String str = ""+rentalDate.format(formatter)+"\t"+car.getCarBrand();
		return str;
	}

	public String getFormattedDetail() {
		String ret = rentalDate.format(dateFormatter) + "\n" +
				car.getFormattedDetail() + "\n";
		switch(bookingStatus) {
		case BOOKING_CANCELED:
			ret += "CANCELLED";
			break;
		case BOOKING_BOOKED:
			ret += "BOOKED";
			break;
		case BOOKING_RETURNED:
			ret += "RETURNED";
			break;
		default:
			ret += "UNKNOWN STATUS";
			break;
		}
		return ret;
	}

	public void setBookingStatus(int status) {
		bookingStatus = status;
	}
	
	public static ArrayList<CustomerRentCar> getDummyOrders(Customer customer) {
		ArrayList<CustomerRentCar> list = new ArrayList<CustomerRentCar>();
		try {
			list = Contractor.getCustomerRentCar();
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static CustomerRentCar createOrder(Customer user, LocalDate date, Car car) {
		CustomerRentCar customerRentCar = null;
		try {
			customerRentCar = Contractor.createOrder(user,date,car);

		} catch (CustomException e) {
			e.printStackTrace();
		}

		return customerRentCar;
	}
}
