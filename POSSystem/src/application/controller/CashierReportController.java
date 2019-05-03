package application.controller;

import javafx.fxml.FXML;

public class CashierReportController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CASHIER REPORT");
	}
}
