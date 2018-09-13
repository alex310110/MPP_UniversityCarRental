package com.carrental.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import com.carrental.controller.DataSourceReciever;
import com.carrental.utils.CustomException;


/* sample call to DB layer

DBLayer.newDBLayer();
			String SQL = "SELECT TOP 10 * FROM UserProfile";
			ResultSet rs = DBLayer.ExecuteSQL(SQL);
			while (rs.next()) {
				System.out.println(rs.getString("userName"));
			}
 */
public class Contractor {
	
	public static Customer getCustomerDetails(String userID) throws CustomException {
		if(userID == null || userID.trim().isEmpty())
			throw new CustomException("Customer ID can not be empty.");
		else
		{
			Model.DBLayer.newDBLayer();
			ResultSet rs = Model.DBLayer.ExecuteSQL("Select * From Customer Where customerID = " + userID);
			boolean isContainsData = false;
					if(rs == null)
					{
						throw new CustomException("Customer ID not found. Please try different ID.");
					}
					else
					{
						try {
							Customer cus = null;
							while (rs.next()) {
								isContainsData = true;
								try {
									cus = new Customer(rs.getLong("customerID"), rs.getString("fName"), rs.getString("lName"),
											rs.getString("gender"), rs.getDate("DOB").toLocalDate(),
											rs.getString("licenseNumber"), rs.getString("universityID"));
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if(isContainsData)
							return cus;
							else
								throw new CustomException("Customer ID not found. Please provide valid customer");
						}
						catch(Exception e)
						{
							throw new CustomException("Customer ID not found. Please try different ID.");
						}


					}
		}
		//return Customer.getDummyUser(userID);
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

	public void cancelOrder(CustomerRentCar order) throws CustomException {
		if (order == null) throw new CustomException("Null order");
		//TODO database query to udpate the data and refresh the list
		order.setBookingStatus(CustomerRentCar.BOOKING_CANCELED);
	}
}
