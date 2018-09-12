package com.carrental.controller;

import java.util.ArrayList;

import com.carrental.models.Car;
import com.carrental.models.CustomerRentCar;

public interface DataSourceReciever {
	
	public void onRecievedOrderList(ArrayList<CustomerRentCar> listOrders);
	
	public void onRecievedAvailableCarList(ArrayList<Car> listCars);
	
	public void onRecievedCarTypes(ArrayList<String> list);


}
