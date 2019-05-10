package application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InventoryDialogController {
	
	@FXML
	private Button cancelButton;
	
	@FXML
	private TextField productField;
	
	@FXML
	private TextField supplierField;
	
	@FXML
	private TextField quantityField;
	
	@FXML
	private TextField pendingField;
	
	@FXML
	private TextField thresholdField;
	
	public void confirmItemDetail(ActionEvent event) {
		
	}
	
	public void cancelItemDetail(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
