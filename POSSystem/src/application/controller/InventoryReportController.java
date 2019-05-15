package application.controller;

import java.util.List;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryReportController {
	@FXML
	private HeaderController headerViewController;

	@FXML
	private TableView<InventoryData> tableData;

	@FXML
	private TableColumn<InventoryData, Integer> productIdColumn;

	@FXML
	private TableColumn<InventoryData, String> productColumn;

	@FXML
	private TableColumn<InventoryData, String> supplierColumn;

	@FXML
	private TableColumn<InventoryData, Double> priceColumn;

	@FXML
	private TableColumn<InventoryData, Integer> quantityColumn;

	@FXML
	private TableColumn<InventoryData, Integer> pendingColumn;

	@FXML
	private TableColumn<InventoryData, Integer> thresholdColumn;

	private ObservableList<InventoryData> inventoryData;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("INVENTORY REPORT");
		
		inventoryData = FXCollections.observableArrayList();

		List<InventoryData> list = InventoryUtils.getAll();
		if (list != null)
			inventoryData.addAll(list);

		productIdColumn.setCellValueFactory(new PropertyValueFactory<>("ProductId"));
		productColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		supplierColumn.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));
		pendingColumn.setCellValueFactory(new PropertyValueFactory<>("OutstandingOrder"));
		thresholdColumn.setCellValueFactory(new PropertyValueFactory<>("Threshold"));

		tableData.setItems(inventoryData);
	}
}
