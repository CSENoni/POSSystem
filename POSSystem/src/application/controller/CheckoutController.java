package application.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import application.Model.POSUtils;
import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import application.Model.Sale.SaleData;
import application.Model.Sale.SaleUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class CheckoutController {
	private SaleData newSale;
	
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
	
	private DecimalFormat decim = new DecimalFormat("#,##0.00");
	
	private ObservableList<InventoryData> saleList;
	
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
	
	public void sendSale(SaleData sale) {
		this.newSale = sale;
	}
	
	public void setSaleList(ObservableList<InventoryData> saleList) {
		this.saleList = saleList;
	}
	
	public void inputPaidValidation(KeyEvent event) {
		if (!isDouble(event.getCharacter())) event.consume();
	}
	
	public void calculateDueAndChange(KeyEvent event) {
		String input = this.totalPaid.getText();
		if (input != null && input.length() > 0) {
			double price = newSale.getSaleTotal();
			double paid = Double.parseDouble(input);
			double amount = price - paid;

			if (amount > 0) {
				totalDue.setText(decim.format(amount));
				totalChange.setText(decim.format(0.0));
			} else if (amount < 0) {
				totalDue.setText(decim.format(0.0));
				totalChange.setText(decim.format(Math.abs(amount)));
			} else {
				totalDue.setText(decim.format(0.0));
				totalChange.setText(decim.format(0.0));
			}
		} else {
			totalDue.setText("");
			totalChange.setText("");
		}
	}
	
	public void completeTransaction(ActionEvent event) throws IOException {
		if(totalDue != null && totalDue.getText().length() > 0) {
			double due = Double.parseDouble(totalDue.getText());
			if(due == 0) {
				this.newSale.setPaid(Double.parseDouble(totalPaid.getText()));
				this.newSale.setChange(Double.parseDouble(totalChange.getText()));
				SaleUtils.writeSale(this.newSale);
				
				for(InventoryData item : saleList) {
					item.setOutstandingOrder(item.getOutstandingOrder() + item.getSaleQuantity());
					item.setSaleQuantity(0);
					InventoryUtils.update(item);
				}
				
				
				POSUtils.changeScene(event, getClass(), "../view/SaleComplete.fxml"); 
			}
		}
	}

	public void toCancel(ActionEvent event) throws IOException {
		headerViewController.toHome(event);
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

