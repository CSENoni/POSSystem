package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CheckoutController {
	@FXML
	private HeaderController headerViewController;

	@FXML
	private Text saleNumber;

	@FXML
	private Text totalPrice;

	@FXML
	private TextField totalPaid;

	@FXML
	private Text totalDue;

	@FXML
	private Text totalChange;

	@FXML
	private void initialize() {
		headerViewController.setTitle("CHECKOUT");
		totalPaid.setText(String.valueOf(0.0));
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber.setText(saleNumber);
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice.setText(totalPrice);
	}
	
	public void inputPaidValidation(KeyEvent event) {
		if (!isDouble(event.getCharacter())) event.consume();
	}
	
	public void calculateDueAndChange(KeyEvent event) {
		String input = this.totalPaid.getText();
		if (input != null && input.length() > 0) {
			double price = Double.parseDouble(this.totalPrice.getText());
			double paid = Double.parseDouble(input);
			double amount = price - paid;

			if (amount > 0) {
				totalDue.setText(String.valueOf(amount));
				totalChange.setText(String.valueOf(0.0));
			} else if (amount < 0) {
				totalDue.setText(String.valueOf(0.0));
				totalChange.setText(String.valueOf(Math.abs(amount)));
			} else {
				totalDue.setText(String.valueOf(0.0));
				totalChange.setText(String.valueOf(0.0));
			}
		} else {
			totalDue.setText("");
			totalChange.setText("");
		}
	}
	
	public void completeTransaction(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleComplete.fxml");
	}

	public void toCancel(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/SaleCancel.fxml");
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
