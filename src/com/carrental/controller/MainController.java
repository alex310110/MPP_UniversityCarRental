package com.carrental.controller;

import java.util.ArrayList;

import com.carrental.models.Car;
import com.carrental.models.CustomerRentCar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements DataSourceReciever {	
	@FXML
	private TextField etUserID;
	@FXML
	private Button btnSwitchUser;
	@FXML
	private Label labelUserID;
	@FXML
	private Label labelUserDetail;
	@FXML
	private TextArea taUserDetail;
	@FXML
	private Label labelOrders;
	@FXML
	private ListView<String> lvOrders;
	@FXML
	private Tab tabOrderDetails;
	@FXML
	private Tab tabRental;
	@FXML
	private TextArea taOrderDetail;
	@FXML
	private Button btnCancelOrder;
	@FXML
	private Label labelRentalDate;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Label labelCarType;
	@FXML
	private ChoiceBox<String> cbCarType;
	@FXML
	private Label labelAvailableCar;
	@FXML
	private ListView<String> lvAvailableCars;
	@FXML
	private Label labelCarDetail;
	@FXML
	private TextArea taCarDetails;
	@FXML
	private Button btnRent;

	@FXML
	protected void onCancelOrder(ActionEvent e) {

	}

	@FXML
	protected void onRentClicked(ActionEvent e) {

	}

	@FXML
	protected void handleSwitchUser(ActionEvent event) {
		
	}

	@Override
	public void onRecievedOrderList(ArrayList<CustomerRentCar> listOrders) {
		
	}

	@Override
	public void onRecievedAvailableCarList(ArrayList<Car> listCars) {
		
	}

	@Override
	public void onRecievedCarsList(ArrayList<String> list) {
		
	}
}
