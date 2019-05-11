package application.controller;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InventoryDialogController {

	@FXML
	private Button cancelButton;

	@FXML
	private TextField productField;

	@FXML
	private TextField supplierField;
	
	@FXML
	private TextField priceField;
	
	@FXML
	private TextField quantityField;

	@FXML
	private TextField thresholdField;
	
	@FXML
	private Text errorMessage;
	
	private ObservableList<InventoryData> data;

	@FXML
	private void initialize() {

	}

	public void setInventoryData(ObservableList<InventoryData> data) {
		this.data = data;
	}

	public void confirmItemDetail(ActionEvent event) {
		if (areValidFields(productField.getText(), supplierField.getText(), priceField.getText(), 
				quantityField.getText(), thresholdField.getText())) {
			errorMessage.setVisible(false);
			String productName = productField.getText();
			String supplier = supplierField.getText();
			double price = Double.parseDouble(priceField.getText());
			int quantity = Integer.parseInt(quantityField.getText());
			int threshold = Integer.parseInt(thresholdField.getText());

			InventoryData newInventoryData = new InventoryData(productName, supplier, price, quantity, threshold);
			InventoryUtils.write(newInventoryData);

			data.add(newInventoryData);
			closeItemDetail(event);
		}else {
			errorMessage.setVisible(true);
		}
	}

	public void closeItemDetail(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	public void priceInputValidation(KeyEvent event) {
		if(!isDouble(event.getCharacter())) event.consume();
	}
	
	public void quantityInputValidation(KeyEvent event) {
		if(!isNumeric(event.getCharacter())) event.consume();
	}
	
	public void thresholdInputValidation(KeyEvent event) {
		if(!isNumeric(event.getCharacter())) event.consume();
	}
	
	private boolean areValidFields(String product, String supplier, String price, String quantity, String threshold) {
		boolean allGood = true;
		String errorStyle = "-fx-border-width: 3,3,3,3; -fx-border-color: red;";
		if (!isNotEmpty(product)) {
			allGood = false;
			productField.setStyle(errorStyle);
		} else {
			productField.setStyle("");
		}

		if (!isNotEmpty(supplier)) {
			allGood = false;
			supplierField.setStyle(errorStyle);
		} else {
			supplierField.setStyle("");
		}
		
		if(!isNotEmpty(price)) {
			allGood = false;
			priceField.setStyle(errorStyle);
		}else {
			priceField.setStyle("");
		}
		
		if (!isNotEmpty(quantity)) {
			allGood = false;
			quantityField.setStyle(errorStyle);
		} else {
			quantityField.setStyle("");
		}

		if (!isNotEmpty(threshold)) {
			allGood = false;
			thresholdField.setStyle(errorStyle);
		} else {
			thresholdField.setStyle("");
		}
		return allGood;
	}

	private boolean isNotEmpty(String text) {
		return text != null && text.length() > 0;
	}

	private boolean isNumeric(String value) {
		String number = value.replaceAll("\\s+", "");
		for (int j = 0; j < number.length(); j++) {
			char c = number.charAt(j);
			if (c > 31 && (c < 48 || c > 57)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isDouble(String value) {
		String number = value.replaceAll("\\s+", "");
		for (int j = 0; j < number.length(); j++) {
			char c = number.charAt(j);
			if (c != 46 && c > 31 && (c < 48 || c > 57)) {
				return false;
			}
		}
		return true;
	}
}
