// enables second screen

package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import application.Model.Inventory.InventoryData;
import application.Model.Sale.SaleData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReturnController {

	SaleData returnSale;
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private Label saleNumber;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
				
	}
	
	@FXML
	private ObservableList<InventoryData> inventorySaleList;
	
	public void addAndEditItem(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/SaleAddItem.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			SaleAddItemController dialogController = fxmlLoader.getController();
			dialogController.setSaleTable(inventorySaleList);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toCompleteReturn(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/ReturnComplete.fxml");
	}
	
	public void sendSale(SaleData sale) {
		this.returnSale = sale;
	}
}
