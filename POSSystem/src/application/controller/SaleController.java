package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SaleController {
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("SALE");
	}
	
	@FXML
	private Label saleNumber;
	
	public void toCheckOut(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Checkout.fxml");
	}
}
