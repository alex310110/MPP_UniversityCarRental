package com.carrental.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
	 @FXML private TextField etUserID;
	 @FXML private Button btnSwitchUser;
	    
	    @FXML protected void handleSwitchUser(ActionEvent event) {
	    	etUserID.setText("Sign in button pressed");
	    }
}
