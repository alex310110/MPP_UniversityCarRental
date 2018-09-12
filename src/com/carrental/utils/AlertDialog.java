package com.carrental.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog {

	public static void showAlert(String alertText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText(alertText);

		alert.showAndWait();
	}
	
	public static void log(String logString) {
		System.out.println(logString);
	}
}
