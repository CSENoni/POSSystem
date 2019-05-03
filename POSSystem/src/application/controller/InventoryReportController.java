package application.controller;

import javafx.fxml.FXML;

public class InventoryReportController {
	@FXML
	private HeaderController headerViewController;

	@FXML
	private void initialize() {
		headerViewController.setTitle("INVENTORY REPORT");
	}
}
