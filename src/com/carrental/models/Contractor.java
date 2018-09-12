package com.carrental.models;

import java.time.LocalDate;
import java.util.ArrayList;

import com.carrental.controller.DataSourceReciever;
import com.carrental.controller.MainController;
import com.carrental.utils.CustomException;

public class Contractor {
	
	public Customer getUserDetails(String userID) throws CustomException {
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
	
	public void getAvailableCars(LocalDate date, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		dataSource.onRecievedAvailableCarList(Car.getTempCarsList());
	}
}
