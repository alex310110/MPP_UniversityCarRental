package com.carrental.models;

import java.time.LocalDate;
import java.util.*;

import com.carrental.controller.DataSourceReciever;
import com.carrental.utils.CustomException;

public class Contractor {
	
	public Customer getUser(String userID) throws CustomException {
		if(userID == null || userID.trim().isEmpty())
			throw new CustomException("Customer ID can not be empty.");
		return Customer.getDummyUser(userID);
	}

	public void getCustomerOrders(Customer customer, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		if(customer == null)
			throw new CustomException("Customer can not be null.");
		dataSource.onRecievedOrderList(CustomerRentCar.getDummyOrders(customer));
		
	}
	
	public void getAvailableCars(LocalDate date, String type, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		List<Car> cars = Car.getTempCarsList();
		List<Car> filtered = new ArrayList<>();
		for (Car c : cars) {
			if(c.getType().equals(type) || type.equals("All")) {
				filtered.add(c);
			}
		}
		dataSource.onRecievedAvailableCarList(filtered);
	}
	
	public void getCarTypes(DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		// mock
		List<String> types = new ArrayList<>();
		types.add("Compact");
		types.add("SUV");
		dataSource.onRecievedCarTypes(types);
	}

	public CustomerRentCar createOrder(Customer user, LocalDate date, Car car) throws CustomException {
		if (user == null || date == null || car == null)
			throw new CustomException("Null user, date or car");
		return CustomerRentCar.createOrder(user, date, car);
	}
}
