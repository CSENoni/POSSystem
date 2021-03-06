package application.controller;

import java.io.IOException;
import java.util.List;

import application.Model.Inventory.InventoryData;
import application.Model.Inventory.InventoryUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryController {
	@FXML
	private HeaderController headerViewController;

	@FXML
	private TableView<InventoryData> tableData;

	@FXML
	private TableColumn<InventoryData, String> productColumn;

	@FXML
	private TableColumn<InventoryData, String> supplierColumn;

	@FXML
	private TableColumn<InventoryData, Double> priceColumn;

	@FXML
	private TableColumn<InventoryData, Integer> quantityColumn;

	@FXML
	private TableColumn<InventoryData, Integer> thresholdColumn;

	private ObservableList<InventoryData> inventoryData;

	@FXML
	private void initialize() {
		headerViewController.setTitle("INVENTORY");
		inventoryData = FXCollections.observableArrayList();

		List<InventoryData> list = InventoryUtils.getAll();
		if (list != null)
			inventoryData.addAll(list);

		productColumn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
		supplierColumn.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("StockQuantity"));
		thresholdColumn.setCellValueFactory(new PropertyValueFactory<>("Threshold"));

		tableData.setItems(inventoryData);
		tableData.setEditable(true);
	}

	public void addNewItem(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/InventoryDialog.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			InventoryDialogController dialogController = fxmlLoader.getController();
			dialogController.setInventoryData(inventoryData, null);

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void editItem(ActionEvent event) {
		if (inventoryData != null && inventoryData.size() > 0 && tableData.getSelectionModel().getSelectedItem() != null) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/InventoryDialog.fxml"));
				Parent root = (Parent) fxmlLoader.load();

				InventoryDialogController dialogController = fxmlLoader.getController();
				InventoryData item = tableData.getSelectionModel().getSelectedItem();
				dialogController.setInventoryData(inventoryData, item);

				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeItem(ActionEvent event) {
		InventoryData item = tableData.getSelectionModel().getSelectedItem();
		if (item != null) {
			inventoryData.remove(item);
			InventoryUtils.remove(item);
		}
	}
}
