package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StartReturnController {

	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
	}
	
	@FXML
	private TextField saleNumber;
	
	public void toReturns(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Return.fxml");
	}
}
