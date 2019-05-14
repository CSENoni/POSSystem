package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("HOME");
	}
	
	public void newSale(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Sale.fxml");
	}
	
	public void toReturn(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/StartReturn.fxml");
	}
	
	public void toInventory(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Inventory.fxml");
	}
	
	public void toReports(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Report.fxml");
	}
}
