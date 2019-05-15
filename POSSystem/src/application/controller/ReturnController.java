// enables second screen

package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import application.Model.Inventory.InventoryData;
import application.Model.Sale.SaleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReturnController {

	SaleData returnSale;
	ObservableList<InventoryData> saleItemList = FXCollections.observableArrayList();;
	
	
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private Label saleNumber;
	
	@FXML
	private TableView<InventoryData> saleTable;
	
	@FXML
	private TableColumn<InventoryData, String> saleProductColumn;
	
	@FXML
	private TableColumn<InventoryData, Integer> saleQuantityColumn;
	
	@FXML
	private TableColumn<InventoryData, String> salePriceColumn;
	
	@FXML
	private TableView<InventoryData> returnTable;
	
	@FXML
	private TableColumn<InventoryData, String> returnProductColumn;
	
	@FXML
	private TableColumn<InventoryData, Integer> returnQuantityColumn;
	
	@FXML
	private TableColumn<InventoryData, Double> returnPriceColumn;
	
	@FXML
	private Label returnTotal;
	
	@FXML
	private void initialize() {
		
		saleProductColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, String>("ProductName"));
		saleQuantityColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, Integer>("SaleQuantity"));
		salePriceColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, String>("PrintSaleTotal"));
		
		saleTable.setItems(saleItemList);
		saleTable.setEditable(true);
		
	}
	
	
	
	public void toCompleteReturn(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/ReturnComplete.fxml");
	}
	
	public void sendSale(SaleData sale) {
		this.returnSale = sale;
		saleNumber.setText(Long.toString(sale.getSaleNumber()));
	}
	
	public void sendSaleItems(ObservableList<InventoryData> itemList) {
		this.saleItemList = itemList;
	}
	
	
	public void setSaleTable(ObservableList<InventoryData> itemList) {
		saleProductColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, String>("ProductName"));
		saleQuantityColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, Integer>("SaleQuantity"));
		salePriceColumn.setCellValueFactory(new PropertyValueFactory<InventoryData, String>("PrintSaleTotal"));
		
		saleTable.setItems(itemList);
		saleTable.setEditable(true);
	}
	
}
