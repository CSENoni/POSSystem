package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InventoryController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("INVENTORY");
	}
	
	public void addNewItem(ActionEvent event) {
		
	}
	
	public void editItem(ActionEvent event) {
		
	}
	
	public void removeItem(ActionEvent event) {
		
	}
}
