package application.controller;

import java.util.List;

import application.Model.Sale.SaleData;
import application.Model.Sale.SaleUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CashierReportController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private TableView<SaleData> tableData;
	
	@FXML
	private TableColumn<SaleData, Integer> saleIDColumn;
	
	@FXML
	private TableColumn<SaleData, Integer> cashierColumn;
	
	@FXML
	private TableColumn<SaleData, Integer> itemColumn;
	
	@FXML
	private TableColumn<SaleData, String> totalColumn;
	
	private ObservableList<SaleData> saleData;

	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CASHIER REPORT");
		saleData = FXCollections.observableArrayList();
		
		List<SaleData> list = SaleUtils.getAll();
		if (list != null)
			saleData.addAll(list);
		
		saleIDColumn.setCellValueFactory(new PropertyValueFactory<>("SaleNumber"));
		cashierColumn.setCellValueFactory(new PropertyValueFactory<>("User"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<>("NumberOfItems"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("PrintSaleTotal"));
		
		tableData.setItems(saleData);
		tableData.setEditable(true);
	}
}
