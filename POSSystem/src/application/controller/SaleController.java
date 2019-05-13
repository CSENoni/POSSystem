package application.controller;

import java.io.IOException;

import application.Model.Inventory.InventoryData;
import application.Model.Sale.SaleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
	private TableView<InventoryData> tableData;
	
	@FXML
	private TableColumn<InventoryData, String> itemColumn;
	
	@FXML
	private TableColumn<InventoryData, Integer> quantityColumn;
	
	@FXML
	private TableColumn<InventoryData, String> priceColumn;
	
	@FXML
	private ObservableList<InventoryData> inventorySaleList;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("SALE");
		
		inventorySaleList = FXCollections.observableArrayList();
		
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("SaleQuantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("PrintSaleTotal"));
		
		tableData.setItems(inventorySaleList);
		tableData.setEditable(true);
		
	}
	
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
	
	public void updateSale(MouseEvent event) {
		newSale.editSaleItems(inventorySaleList);
		saleNumber.setText(this.getSaleNumber());
		totalPrice.setText(this.getSaleTotal());
	}
	
	public void toCheckOut(ActionEvent event) throws IOException {
		if(inventorySaleList != null && inventorySaleList.size() > 0) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Checkout.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			CheckoutController controller = fxmlLoader.getController();
			controller.setSaleNumber(getSaleNumber());
			controller.setTotalPrice(getSaleTotal());
			
			Scene scene = new Scene(root);
			
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		}
	}
	
	public String getSaleNumber() {
		return Integer.toString(newSale.getSaleNumber());
	}
	public String getSaleTotal() {
		return newSale.printSaleTotal();
	}
}
