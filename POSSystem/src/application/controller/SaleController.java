package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import application.Model.Sale.SaleData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaleController {
	
	private SaleData newSale = new SaleData();
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private Text saleNumber;
	
	@FXML
	private Text totalPrice;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("SALE");
		saleNumber.setText(this.getSaleNumber());
		totalPrice.setText(this.getSaleTotal());
	}
	
	public void addAndEditItem(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/SaleAddItem.fxml"));
			Parent root = (Parent) fxmlLoader.load();
//			SaleAddItemController dialogController = fxmlLoader.getController();

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toCheckOut(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Checkout.fxml");
	}
	
	public String getSaleNumber() {
		return Integer.toString(newSale.getSaleNumber());
	}
	public String getSaleTotal() {
		return newSale.printSaleTotal();
	}
}
