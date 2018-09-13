package com.carrental.app;

import com.carrental.controller.MainController;
import com.carrental.models.DBLayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	MainController controller;
	private static final String SOURCE_FXML_NAME = "car_rental_ui.fxml";
	

	@Override
	public void start(Stage primaryStage) {
		try {

			// The newInstance() call is a work around for some
			// broken Java implementations
			if(!DBLayer.isSQLServer)
				Class.forName("com.mysql.jdbc.Driver").newInstance();

			Parent root = FXMLLoader.load(getClass().getResource(SOURCE_FXML_NAME));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
