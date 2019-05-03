package application.controller;

import javafx.fxml.FXML;

public class RegisterReportController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("REGISTER REPORT");
	}
}
