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
		if (userID == null || userID.trim().isEmpty())
			throw new CustomException("Customer ID can not be empty.");
		
		DBLayer.newDBLayer();
		ResultSet rs = DBLayer.ExecuteSQL("Select * From Customer Where customerID = " + userID);
		boolean isContainsData = false;
		if (rs == null)
			throw new CustomException("Customer ID not found. Please try different ID.");
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
			if (isContainsData)
				return cus;
			else
				throw new CustomException("Customer ID not found. Please provide valid customer");
		} catch (Exception e) {
			throw new CustomException("Customer ID not found. Please try different ID.");
		}
	}

	public static void getAvailableCars(LocalDate date, String type, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		
		DBLayer.newDBLayer();
		List<Car> cars = new ArrayList<Car>();
		String sql = "Select * From Car where " +
			(
				// inextensible schema!!!
				type.equals("All") ? "" :
				type.equals("SUV") ? "Car.isSUV = 1 and " :
				"Car.isSUV = 0 and "
			) +
			"carID not in (" +
			"select distinct carID from CustomerRentCar " +
			"where bookingStatus = 1 and rentalDate = '" +
			date.toString() + "')";
		ResultSet rs = DBLayer.ExecuteSQL(sql);
		
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
		dataSource.onRecievedAvailableCarList(cars);
	}

	public static void getCustomerOrders(Customer customer, DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		if(customer == null)
			throw new CustomException("Customer can not be null.");
		
		ArrayList<CustomerRentCar> list = new ArrayList<CustomerRentCar>();
		Customer cus = null;
		Car car = null;
		DBLayer.newDBLayer();
		LocalDate rentalDate;
		int rentalStatus;
		long CRC_ID ;
		try {
			ResultSet rs = DBLayer.ExecuteSQL(
				"Select * From " +
				"Customer Cus inner join CustomerRentCar CRC on CRC.customerID =Cus.customerID " +
				"Inner join Car C on C.carID = CRC.carID " +
				"where Cus.customerID = " + customer.getCustomerID() +
				" order by rentalDate desc");
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
			dataSource.onRecievedOrderList(list);
		}
		catch(Exception e)
		{
			throw new CustomException("Customer ID not found. Please try different ID.");
		}
	}

	public static void getCarTypes(DataSourceReciever dataSource) throws CustomException {
		if(dataSource == null) return;
		List<String> types = new ArrayList<>();
		types.add("Compact");
		types.add("SUV");
		dataSource.onRecievedCarTypes(types);
	}

	public static CustomerRentCar createOrder(Customer user, LocalDate date, Car car) throws CustomException {
		if (user == null || date == null || car == null)
			throw new CustomException("Null user, date or car");
		DBLayer.ExecuteQuery("Insert into CustomerRentCar(carID,customerID,rentalDate,bookingStatus,CRC_ID) Select "+car.getCarID() + "," + user.getCustomerID()+ "," + "'" + date+"'" +"," + CustomerRentCar.BOOKING_BOOKED + ",(Select CASE MAX(CRC_ID) WHEN NULL THEN 1 ELSE (MAX(CRC_ID) + 1) END From CustomerRentCar)");
		ResultSet rs = DBLayer.ExecuteSQL("select MAX(CRC_ID) from CustomerRentCar");
		long newCrcId;
		try {
			rs.next();
			newCrcId = rs.getLong("");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("Failed to create new order.");
		}
		CustomerRentCar customerRentCar = CustomerRentCar.setCustomerRentCar(user,car,date,CustomerRentCar.BOOKING_BOOKED,newCrcId);

		return customerRentCar;
	}

	public static void cancelOrder(CustomerRentCar order) throws CustomException {
		if (order == null) throw new CustomException("Null order");
		DBLayer.ExecuteQuery("Update CustomerRentCar Set bookingStatus = " + CustomerRentCar.BOOKING_CANCELED  + " Where  CRC_ID = " + order.getCRC_ID());
		order.setBookingStatus(CustomerRentCar.BOOKING_CANCELED);
	}

}
