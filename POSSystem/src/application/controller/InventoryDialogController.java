package application.controller;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.ObservableList;
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
	private TextField thresholdField;
	
	private ObservableList<InventoryData> data;
	
	public void setInventoryData(ObservableList<InventoryData> data) {
		this.data = data;
	}
	
	public void confirmItemDetail(ActionEvent event) {
		String productName = productField.getText();
		String supplier = supplierField.getText();
		int quantity = Integer.parseInt(quantityField.getText());
		int threshold = Integer.parseInt(thresholdField.getText());
		
		InventoryData newInventoryData = new InventoryData(productName, supplier, 1200, quantity, threshold);
		InventoryUtils.write(newInventoryData);
		
		data.add(newInventoryData);
		closeItemDetail(event);
	}
	
	public void closeItemDetail(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
