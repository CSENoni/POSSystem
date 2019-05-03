package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;

public class ReportController {
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
