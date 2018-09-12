package com.carrental.controller;

import java.time.LocalDate;
import java.util.*;

import com.carrental.models.Car;
import com.carrental.models.Contractor;
import com.carrental.models.Customer;
import com.carrental.models.CustomerRentCar;
import com.carrental.utils.AlertDialog;
import com.carrental.utils.CustomException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class MainController implements DataSourceReciever {
	Contractor modelLayer = new Contractor();
	Customer customer;
	
	@FXML
	private TabPane infoTabPane;
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
	private ListView<CustomerRentCar> lvOrders;
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
	private ListView<Car> lvAvailableCars;
	@FXML
	private Label labelCarDetail;
	@FXML
	private TextArea taCarDetail;
	@FXML
	private Button btnRent;

	@FXML
	protected void onCancelOrder(ActionEvent e) {
		btnCancelOrder.setDisable(true);
		//todo database query to udpate the data and refresh the list
	}

	@FXML
	protected void onClickNewRental() {
		if (datePicker.getValue() == null)
			datePicker.setValue(LocalDate.now());
		try {
			modelLayer.getCarTypes(this);
			cbCarType.setValue("All");
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lvAvailableCars.getItems().clear();
		taCarDetail.clear();
		btnRent.setDisable(true);
	}

	@FXML
	protected void onQueryClicked() {
		try {
			modelLayer.getAvailableCars(datePicker.getValue(),
					cbCarType.getValue(), this);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taCarDetail.clear();
		btnRent.setDisable(true);
	}
	
	@FXML
	protected void onRentClick(ActionEvent e) {
		try {
			CustomerRentCar order = modelLayer.createOrder(customer, datePicker.getValue(),
					lvAvailableCars.getSelectionModel().getSelectedItem());
			modelLayer.getCustomerOrders(customer, this);
			infoTabPane.getSelectionModel().select(tabOrderDetails);
			taOrderDetail.setText(order.getFormattedDetail());
			// TODO select the order after DB works
			btnCancelOrder.setDisable(false);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
    protected void handleViewOrder(MouseEvent arg0) {
		CustomerRentCar order = lvOrders.getSelectionModel().getSelectedItem();
		if (order == null) {
			taOrderDetail.clear();
			btnCancelOrder.setDisable(true);
		}
		else {
			taOrderDetail.setText(order.getFormattedDetail());
			
			if(order.getBookingStatus() == CustomerRentCar.BOOKING_BOOKED)
				btnCancelOrder.setDisable(false);
			else 
				btnCancelOrder.setDisable(true);
		}
    }
	
	@FXML
    protected void onClickCarListItem(MouseEvent arg0) {
		Car car = lvAvailableCars.getSelectionModel().getSelectedItem();
		if (car == null) {
			taCarDetail.clear();
			btnRent.setDisable(true);
		}
		else {
			taCarDetail.setText(car.getFormattedDetail());
			btnRent.setDisable(false);
		}
    }
	
	@FXML
	protected void handleSwitchUser(ActionEvent event) {
		try {

			 customer = modelLayer.getCustomerDetails(etUserID.getText().toString());
			taUserDetail.setText(customer.toString());
			modelLayer.getCustomerOrders(customer, this);
		} catch (CustomException e) {
			lvOrders.getItems().clear();
			taUserDetail.clear();
			AlertDialog.showAlert(e.getLocalizedMessage());
		}
		taOrderDetail.clear();
		btnCancelOrder.setDisable(true);
	}

	@Override
	public void onRecievedOrderList(List<CustomerRentCar> listOrders) {
		lvOrders.getItems().clear();
		for (CustomerRentCar cRC : listOrders)
			lvOrders.getItems().add(cRC);
	}

	@Override
	public void onRecievedAvailableCarList(List<Car> cars) {
		lvAvailableCars.getItems().clear();
		lvAvailableCars.getItems().addAll(cars);
	}

	@Override
	public void onRecievedCarTypes(List<String> list) {
		cbCarType.getItems().clear();
		cbCarType.getItems().add("All");
		cbCarType.getItems().addAll(list);
	}
}
