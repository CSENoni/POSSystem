package application.controller;

import javafx.fxml.FXML;

public class SaleCancelController {

	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("SALE");
	}
	
}
