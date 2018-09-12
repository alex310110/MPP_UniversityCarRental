package com.carrental.controller;

import java.util.ArrayList;

import com.carrental.models.Car;
import com.carrental.models.Contractor;
import com.carrental.models.Customer;
import com.carrental.models.CustomerRentCar;
import com.carrental.utils.AlertDialog;
import com.carrental.utils.CustomException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainController implements DataSourceReciever {
	
	Contractor modelLayer = new Contractor();
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
	protected void onRentClick(ActionEvent e) {
		
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
			btnCancelOrder.setDisable(false);
		}
    }
	
	@FXML
	protected void handleSwitchUser(ActionEvent event) {
		try {
			Customer customer = modelLayer.getUserDetails(etUserID.getText().toString());
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
	public void onRecievedOrderList(ArrayList<CustomerRentCar> listOrders) {
		lvOrders.getItems().clear();
		for (CustomerRentCar cRC : listOrders)
////<<<<<<< HEAD
//			lvOrders.getItems().add(cRC.toString());
		
//=======
			lvOrders.getItems().add(cRC);
//>>>>>>> d004c9417fb0dcc611c622b3f47716201d189ecd
	}

	@Override
	public void onRecievedAvailableCarList(ArrayList<Car> listCars) {

	}

	@Override
	public void onRecievedCarsList(ArrayList<String> list) {

	}
}
