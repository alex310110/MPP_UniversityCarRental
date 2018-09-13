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

	public static void getAvailableCars(LocalDate date, String type, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		List<Car> cars = new ArrayList<Car>();
		Model.DBLayer.newDBLayer();
		ResultSet rs = Model.DBLayer.ExecuteSQL("Select * From Car");
		try {
			while (rs.next()) {

				Car car;
				if (rs.getInt("isSUV") == 1) {
					car = new SUV(rs.getLong("carID"),
							rs.getString("carBrand"),
							rs.getString("registerationNumber"),
							rs.getString("color"),
							rs.getInt("rentalFees"));
				} else {
					car = new Compact(rs.getLong("carID"),
							rs.getString("carBrand"),
							rs.getString("registerationNumber"),
							rs.getString("color"),
							rs.getInt("rentalFees"));
				}
				cars.add(car);

			}
		}
		catch(Exception e)
		{
			throw new CustomException("Car data not found.");
		}
		List<Car> filtered = new ArrayList<>();
		for (Car c : cars) {
			if(c.getType().equals(type) || type.equals("All")) {
				filtered.add(c);
			}
		}
		dataSource.onRecievedAvailableCarList(filtered);
	}

	public void getCustomerOrders(Customer customer, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		if(customer == null)
			throw new CustomException("Customer can not be null.");
		dataSource.onRecievedOrderList(CustomerRentCar.getDummyOrders(customer));

	}

	public static ArrayList<CustomerRentCar> getCustomerRentCar() throws CustomException
	{
		ArrayList<CustomerRentCar> list = new ArrayList<CustomerRentCar>();
		Customer cus = null;
		Car car = null;
		Model.DBLayer.newDBLayer();
		LocalDate rentalDate;
		int rentalStatus;
		long CRC_ID ;
		try {
			ResultSet rs = Model.DBLayer.ExecuteSQL("Select * From Customer Cus inner join CustomerRentCar CRC on CRC.customerID =Cus.customerID Inner join Car C on C.carID = CRC.carID");
			while (rs.next()) {
				cus = new Customer(rs.getLong("customerID"), rs.getString("fName"), rs.getString("lName"),
						rs.getString("gender"), rs.getDate("DOB").toLocalDate(),
						rs.getString("licenseNumber"), rs.getString("universityID"));
				if (rs.getInt("isSUV") == 1) {
					car = new SUV(rs.getLong("carID"),
							rs.getString("carBrand"),
							rs.getString("registerationNumber"),
							rs.getString("color"),
							rs.getInt("rentalFees"));
				} else {
					car = new Compact(rs.getLong("carID"),
							rs.getString("carBrand"),
							rs.getString("registerationNumber"),
							rs.getString("color"),
							rs.getInt("rentalFees"));
				}
				rentalDate = rs.getDate("rentalDate").toLocalDate();
				rentalStatus =rs.getInt("bookingStatus");
				CRC_ID =rs.getLong("CRC_ID");

				CustomerRentCar customerRentCar = CustomerRentCar.setCustomerRentCar(cus,car,rentalDate,rentalStatus,CRC_ID);
				list.add(customerRentCar);

			}
			return list;
		}
		catch(Exception e)
		{
			throw new CustomException("Customer ID not found. Please try different ID.");
		}

		/*list.add(new CustomerRentCar(customer,
				new Compact(1, "Toyota", "11A", "Black", 50),
				LocalDate.of(2018, 7, 1), BOOKING_BOOKED));
		list.add(new CustomerRentCar(customer,
				new SUV(1, "Suzuki", "192AAS", "White", 100),
				LocalDate.of(2018, 7, 7), BOOKING_BOOKED));
		list.add(new CustomerRentCar(customer,
				new Compact(1, "Nissan", "ACC11", "Golden", 40),
				LocalDate.of(2018, 7, 9), BOOKING_CANCELED));
		list.add(new CustomerRentCar(customer,
				new SUV(1, "Honda", "JES1124", "Black", 1000),
				LocalDate.of(2018, 7, 11), BOOKING_RETURNED));

		return list;*/
	}

	public void getCarTypes(DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		// mock
		List<String> types = new ArrayList<>();
		types.add("Compact");
		types.add("SUV");
		dataSource.onRecievedCarTypes(types);
	}

	public static CustomerRentCar createOrder(Customer user, LocalDate date, Car car) throws CustomException {
		if (user == null || date == null || car == null)
			throw new CustomException("Null user, date or car");
		CustomerRentCar customerRentCar = CustomerRentCar.setCustomerRentCar(user,car,date,1,0);
		Model.DBLayer.ExecuteQuery("Insert into CustomerRentCar(carID,customerID,rentalDate,bookingStatus,CRC_ID) Select "+car.getCarID() + "," + user.getCustomerID()+ "," + "'" + date+"'" +"," + CustomerRentCar.BOOKING_BOOKED + ",(Select CASE MAX(CRC_ID) WHEN NULL THEN 1 ELSE (MAX(CRC_ID) + 1) END From CustomerRentCar)");

		return customerRentCar;
	}

	public void cancelOrder(CustomerRentCar order) throws CustomException {
		if (order == null) throw new CustomException("Null order");
		//TODO database query to udpate the data and refresh the list
		Model.DBLayer.ExecuteQuery("Update CustomerRentCar Set bookingStatus = " + CustomerRentCar.BOOKING_CANCELED  + " Where  CRC_ID = " + order.getCRC_ID());

		order.setBookingStatus(CustomerRentCar.BOOKING_CANCELED);
	}

}
