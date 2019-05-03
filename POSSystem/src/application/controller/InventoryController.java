package application.controller;

import javafx.fxml.FXML;

public class InventoryController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("INVENTORY");
	}
}
