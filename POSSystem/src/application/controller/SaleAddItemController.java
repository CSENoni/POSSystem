package application.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
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
	private TableColumn<InventoryData, String> productPrice;

	@FXML
	private TableView<InventoryData> saleTable;

	@FXML
	private TableColumn<InventoryData, String> saleProduct;

	@FXML
	private TableColumn<InventoryData, Integer> saleQuantity;

	@FXML
	private TableColumn<InventoryData, String> salePrice;

	@FXML
	private Spinner<Integer> quantity;

	@FXML
	private Button applyButton;

	@FXML
	private Button cancelButton;

	private ObservableList<InventoryData> inventoryList;
	private ObservableList<InventoryData> saleList = FXCollections.observableArrayList();

	private ObservableList<InventoryData> originalSaleList;
	private Set<Integer> changedItemPos;

	@FXML
	private void initialize() {
//		initSpinner(1);
		changedItemPos = new HashSet<Integer>();
		inventoryList = FXCollections.observableArrayList();

		List<InventoryData> list = InventoryUtils.getAll();
		if (list != null) {
			inventoryList.addAll(list);
			for (InventoryData item : list) {
				if (item.isOnSale())
					saleList.add(item);
			}
		}

		productName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		productPrice.setCellValueFactory(new PropertyValueFactory<>("PrintPrice"));
		stockQuantity.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));

		inventoryTable.setItems(inventoryList);
		inventoryTable.setEditable(true);

		saleProduct.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		saleQuantity.setCellValueFactory(new PropertyValueFactory<>("SaleQuantity"));
		salePrice.setCellValueFactory(new PropertyValueFactory<>("PrintSaleTotal"));

		saleTable.setItems(saleList);
		saleTable.setEditable(true);

		inventoryTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				initSpinner(newSelection.getStockQuantity());
				saleTable.getSelectionModel().clearSelection();
			}
		});

		saleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				initSpinner(newSelection.getSaleQuantity());
				inventoryTable.getSelectionModel().clearSelection();
			}
		});
	}

	public void setSaleTable(ObservableList<InventoryData> originalDataList) {
		this.originalSaleList = originalDataList;
	}

	public void addItemToSale() {
		InventoryData item = inventoryTable.getSelectionModel().getSelectedItem();
		if (item != null && item.getStockQuantity() > 0) {
			if ((item.getStockQuantity() - quantity.getValue()) < item.getThreshold() && item.getOutstandingOrder() <= 0) {
				Alert alert = new Alert(AlertType.WARNING,
						"This product quantity will be below the threshold. Would you like to stop and make a re-order now?",
						ButtonType.YES, ButtonType.NO);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					item.setOutstandingOrder(item.getOutstandingOrder() + 1);
				}
				else {
					if (!saleList.contains(item)) {
						item.setSaleQuantity(quantity.getValue());
						saleList.add(item);
					} else {
						item.setSaleQuantity(item.getSaleQuantity() + quantity.getValue());
						saleList.set(saleList.indexOf(item), item);
					}

					if (item.getStockQuantity() - quantity.getValue() <= 0) {
						item.setStockQuantity(0);
					}
					else item.setStockQuantity(item.getStockQuantity() - quantity.getValue());
					int idx = inventoryList.indexOf(item);
					inventoryList.set(idx, item);
					changedItemPos.add(idx);
				}
			}
			else {
				if (!saleList.contains(item)) {
					item.setSaleQuantity(quantity.getValue());
					saleList.add(item);
				} else {
					item.setSaleQuantity(item.getSaleQuantity() + quantity.getValue());
					saleList.set(saleList.indexOf(item), item);
				}

				item.setStockQuantity(item.getStockQuantity() - quantity.getValue());
				int idx = inventoryList.indexOf(item);
				inventoryList.set(idx, item);
				changedItemPos.add(idx);
			}
		}
	}

	public void removeItemFromSale() {
		InventoryData item = saleTable.getSelectionModel().getSelectedItem();
		if (item != null) {
			if (this.getQuantity() >= item.getSaleQuantity()) {
				item.setStockQuantity(item.getStockQuantity() + item.getSaleQuantity());
				item.setSaleQuantity(0);
				saleList.remove(item);
			} else {
				item.setStockQuantity(item.getStockQuantity() + quantity.getValue());
				item.setSaleQuantity(item.getSaleQuantity() - quantity.getValue());
				saleList.set(saleList.indexOf(item), item);
			}
			int idx = inventoryList.indexOf(item);
			inventoryList.set(idx, item);
			changedItemPos.add(idx);
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

	public void confirmSale(ActionEvent event) {
		if (saleList != null && originalSaleList != null) {
			if (originalSaleList.size() == 0) {
				originalSaleList.addAll(saleList);
			} else {
				originalSaleList.setAll(saleList);
			}

			for (int idx : changedItemPos) {
				InventoryUtils.update(idx, inventoryList.get(idx));
			}
		}
		cancelAddingItems(event);
	}
}
