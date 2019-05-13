package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CheckoutController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private Text saleNumber;
	
	@FXML
	private Text totalPrice;
	
	@FXML
	private TextField totalPaid;
	
	@FXML
	private Text totalDue;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CHECKOUT");
	}
	
	public void setSaleNumber(String saleNumber) {
		this.saleNumber.setText(saleNumber);
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice.setText(totalPrice);
	}
	
	public void completeTransaction(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleComplete.fxml");
	}
	
	public void toCancel(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleCancel.fxml");
	}
}
