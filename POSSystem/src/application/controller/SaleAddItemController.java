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
	private TableColumn<InventoryData, String> productName;
	
	@FXML 
	private TableColumn<InventoryData, Integer> stockQuantity;
	
	@FXML 
	private TableColumn<InventoryData, Double> productPrice;
	
	@FXML
	private TableView<InventoryData> saleTable;
	
	@FXML 
	private TableColumn<InventoryData, String> saleProduct;
	
	@FXML 
	private TableColumn<InventoryData, Integer> saleQuantity;
	
	@FXML 
	private TableColumn<InventoryData, Double> salePrice;
	
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
//		initSpinner(1);
		inventoryList = FXCollections.observableArrayList();

		List<InventoryData> list = InventoryUtils.getAll();
		if (list != null)
			inventoryList.addAll(list);

		productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		stockQuantity.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));
		
		inventoryTable.setItems(inventoryList);
		inventoryTable.setEditable(true);
		
		inventoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				initSpinner(newSelection.getStockQuantity());
				saleTable.getSelectionModel().clearSelection();
			}
		});
		
		saleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null) {
				initSpinner(newSelection.getSaleQuantity());
				inventoryTable.getSelectionModel().clearSelection();
			}
		});
	}
	
	public void addItemToSale() {
		InventoryData item = inventoryTable.getSelectionModel().getSelectedItem();
		if(item != null) {
			if (!saleList.contains(item)) {
				item.setSaleQuantity(quantity.getValue());
				saleList.add(item);
			}
			else {
				item.setSaleQuantity(item.getSaleQuantity() + quantity.getValue());
				saleList.set(saleList.indexOf(item), item);
			}
			item.setStockQuantity(item.getStockQuantity() - quantity.getValue());
			inventoryList.set(inventoryList.indexOf(item), item);
			
			saleProduct.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
			saleQuantity.setCellValueFactory(new PropertyValueFactory<>("SaleQuantity"));
			salePrice.setCellValueFactory(new PropertyValueFactory<>("SaleTotal"));
			
			saleTable.setItems(saleList);
			saleTable.setEditable(true);
		}
	}
	
	public void removeItemFromSale() {
		InventoryData item = saleTable.getSelectionModel().getSelectedItem();
		if(item != null) {
			if (this.getQuantity() >= item.getSaleQuantity()) {
				item.setStockQuantity(item.getStockQuantity() + item.getSaleQuantity());
				item.setSaleQuantity(0);
				saleList.remove(item);
			}else {
				item.setStockQuantity(item.getStockQuantity() + quantity.getValue());
				item.setSaleQuantity(item.getSaleQuantity() - quantity.getValue());
				saleList.set(saleList.indexOf(item), item);
			}
			inventoryList.set(inventoryList.indexOf(item), item);
		}
	}
	
	private void initSpinner(int stockQuantity) {
		// Restrict the quantity to add to the sale
        quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stockQuantity));
    }
	
	public int getQuantity() {
		return quantity.getValue();
	}
	
	public void cancelAddingItems(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
