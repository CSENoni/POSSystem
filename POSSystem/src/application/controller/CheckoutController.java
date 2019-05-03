package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CheckoutController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CHECKOUT");
	}
	
	public void completeTransaction(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleComplete.fxml");
	}
}
