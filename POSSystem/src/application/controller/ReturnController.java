// enables second screen

package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReturnController {

	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
	}
	
	@FXML
	private Label saleNumber;
	
	public void toCompleteReturn(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/ReturnComplete.fxml");
	}

}
