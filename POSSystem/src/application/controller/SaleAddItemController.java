package application.controller;

import java.util.List;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SaleAddItemController {
	
	@FXML
	private TableView<InventoryData> inventoryTable;
	
	@FXML 
	private TableColumn productName;
	
	@FXML 
	private TableColumn stockQuantity;
	
	@FXML 
	private TableColumn productPrice;
	
	@FXML
	private TableView<InventoryData> saleTable;
	
	@FXML 
	private TableColumn saleProduct;
	
	@FXML 
	private TableColumn saleQuantity;
	
	@FXML 
	private TableColumn salePrice;
	
	@FXML
	private Spinner<Integer> quantity;
	
	@FXML
	private Button applyButton;
	
	@FXML
	private Button cancelButton;
	
	private ObservableList<InventoryData> inventoryList;
	private ObservableList<InventoryData> saleList = FXCollections.observableArrayList();
	
	
	@FXML
	private void initialize() {
		initSpinner();
		inventoryList = FXCollections.observableArrayList();

		List<InventoryData> list = InventoryUtils.getAll();
		if (list != null)
			inventoryList.addAll(list);

		productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		stockQuantity.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));
		
		inventoryTable.setItems(inventoryList);
		inventoryTable.setEditable(true);
	}
	
	public void addItemToSale() {
		InventoryData item = inventoryTable.getSelectionModel().getSelectedItem();
		if (!saleList.contains(item)) {
			item.setSaleQuantity(quantity.getValue());
			saleList.add(item);
		}
		else {
			item.setSaleQuantity(item.getSaleQuantity() + quantity.getValue());
			saleList.set(saleList.indexOf(item), item);
		}
		
		saleProduct.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		saleQuantity.setCellValueFactory(new PropertyValueFactory<>("SaleQuantity"));
		salePrice.setCellValueFactory(new PropertyValueFactory<>("SaleTotal"));
		
		saleTable.setItems(saleList);
		saleTable.setEditable(true);
	}
	
	private void initSpinner() {
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99));
    }
	
	public int getQuantity() {
		return quantity.getValue();
	}
	
	public void cancelAddingItems(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	
}
