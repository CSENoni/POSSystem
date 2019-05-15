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
	private TableColumn<SaleData, Integer> cashierColumn;
	
	@FXML
	private TableColumn<SaleData, String> registerColumn;
	
	@FXML
	private TableColumn<SaleData, String> typeColumn;
	
	@FXML
	private TableColumn<SaleData, Long> saleIDColumn;
	
	@FXML
	private TableColumn<SaleData, String> saleDateColumn;
	
	@FXML
	private TableColumn<SaleData, Integer> itemColumn;
	
	@FXML
	private TableColumn<SaleData, String> totalColumn;
	
	@FXML
	private TableColumn<SaleData, Double> paidColumn;
	
	@FXML
	private TableColumn<SaleData, Double> changeColumn;
	
	private ObservableList<SaleData> saleData;

	
	@FXML
	private void initialize() {
		headerViewController.setTitle("CASHIER REPORT");
		saleData = FXCollections.observableArrayList();
		
		List<SaleData> list = SaleUtils.getAll();
		if (list != null) {
			saleData.addAll(list);
		}
		
		cashierColumn.setCellValueFactory(new PropertyValueFactory<SaleData, Integer>("UserId"));
		registerColumn.setCellValueFactory(new PropertyValueFactory<SaleData, String>("RegisterId"));
		typeColumn.setCellValueFactory(new PropertyValueFactory<SaleData, String>("Type"));
		saleIDColumn.setCellValueFactory(new PropertyValueFactory<SaleData, Long>("SaleNumber"));
		saleDateColumn.setCellValueFactory(new PropertyValueFactory<SaleData, String>("SaleTime"));
		itemColumn.setCellValueFactory(new PropertyValueFactory<SaleData, Integer>("numItems"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<SaleData, String>("printSaleTotal"));
		paidColumn.setCellValueFactory(new PropertyValueFactory<SaleData, Double>("PrintPaid"));
		changeColumn.setCellValueFactory(new PropertyValueFactory<SaleData, Double>("PrintChange"));
		
		tableData.setItems(saleData);
		tableData.setEditable(true);
	}
}
