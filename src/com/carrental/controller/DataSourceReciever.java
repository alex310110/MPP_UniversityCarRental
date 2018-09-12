package com.carrental.controller;

import java.util.*;

import com.carrental.models.Car;
import com.carrental.models.CustomerRentCar;

public interface DataSourceReciever {
	
	public void onRecievedOrderList(List<CustomerRentCar> listOrders);
	
	public void onRecievedAvailableCarList(List<Car> listCars);
	
	public void onRecievedCarTypes(List<String> list);


}
