// enables first screen

package application.controller;

import java.io.IOException;
import java.util.List;

import application.Model.Inventory.InventoryData;
import application.Model.Sale.SaleData;
import application.Model.Sale.SaleUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class StartReturnController {

	@FXML
	private HeaderController headerViewController;

	@FXML
	private TextField saleNumber;

	private ObservableList<SaleData> saleData;
	private ObservableList<InventoryData> saleItems = FXCollections.observableArrayList();
	private SaleData returnSale;

	@FXML
	private void initialize() {
		headerViewController.setTitle("RETURN");
		saleData = FXCollections.observableArrayList();

		List<SaleData> list = SaleUtils.getAll();
		if (list != null) {
			saleData.addAll(list);
		}

	}
	
	public void inputValidation(KeyEvent event) {
		if(!(isNumeric(event.getCharacter()))) event.consume();
	}
	
	public void toReturn(ActionEvent event) throws IOException {
		String n = saleNumber.getText();
		long saleNum = 0;
		
		if(n != null && n.length() > 0)
			saleNum = Long.parseLong(saleNumber.getText());
		
		for (SaleData sale : saleData) {
			if (saleNum == sale.getSaleNumber()) {
				this.returnSale = sale;
				break;
			}
		}

		if (this.returnSale != null) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Return.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			ReturnController controller = fxmlLoader.getController();
			controller.sendSale(this.returnSale);
			controller.setSaleTable(getItems());

			Scene scene = new Scene(root);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		}
	}

	public ObservableList<InventoryData> getItems() {
		for (InventoryData item : this.returnSale.getSaleItems()) {
			saleItems.add(item);
		}
		return saleItems;
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
}