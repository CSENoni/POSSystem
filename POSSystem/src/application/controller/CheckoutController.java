package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class CheckoutController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CHECKOUT");
	}
	
	@FXML
	private Text saleNumber;
	
	public void completeTransaction(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleComplete.fxml");
	}
	
	public void toCancel(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleCancel.fxml");
	}
}
