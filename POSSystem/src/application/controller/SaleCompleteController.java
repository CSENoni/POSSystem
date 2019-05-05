package application.controller;

import javafx.fxml.FXML;

public class SaleCompleteController {

	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("SALE");
	}
	
}
