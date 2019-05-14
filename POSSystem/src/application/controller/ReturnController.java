// enables second screen

package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import application.Model.Sale.SaleData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReturnController {

	SaleData returnSale;
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private Label saleNumber;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
				
	}
	
	
	
	public void toCompleteReturn(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/ReturnComplete.fxml");
	}
	
	public void sendSale(SaleData sale) {
		this.returnSale = sale;
	}
}
