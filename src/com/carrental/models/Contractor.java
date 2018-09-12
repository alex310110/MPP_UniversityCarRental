package com.carrental.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.carrental.controller.DataSourceReciever;
import com.carrental.controller.MainController;
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
					if(rs == null)
					{
						throw new CustomException("Customer ID not found. Please try different ID.");
					}
					else
					{
						try {
							Customer cus = null;
							while (rs.next()) {

								try {
									cus = new Customer(rs.getLong("customerID"), rs.getString("fName"), rs.getString("lName"),
											rs.getString("gender"), rs.getDate("DOB").toLocalDate(),
											rs.getString("licenseNumber"), rs.getString("universityID"));
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							return cus;
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
	
	public void getAvailableCars(LocalDate date, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		dataSource.onRecievedAvailableCarList(Car.getTempCarsList());
	}
}
