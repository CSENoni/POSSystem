package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReportController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("REPORTS");
	}
	
	public void toCashierReport(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/CashierReport.fxml");
	}
	
	public void toInventoryReport(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/InventoryReport.fxml");
	}
	
	public void toRegisterReport(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/RegisterReport.fxml");
	}
}
