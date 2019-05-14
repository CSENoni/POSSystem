// enables second screen

package application.controller;

import javafx.fxml.FXML;

public class ReturnCompleteController {
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
	}
}
