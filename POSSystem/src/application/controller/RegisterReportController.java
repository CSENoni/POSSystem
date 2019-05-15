package application.controller;

import java.util.Date;
import java.util.List;

import application.Model.Register.RegisterData;
import application.Model.Register.RegisterUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegisterReportController {
	@FXML
	private HeaderController headerViewController;
	
	@FXML
	private TableView<RegisterData> tableData;
	
	@FXML
	private TableColumn<RegisterData, String> registerIdColumn;
	
	@FXML
	private TableColumn<RegisterData, Integer> userIdColumn;
	
	@FXML
	private TableColumn<RegisterData, Date> dateColumn;
	
	private ObservableList<RegisterData> registerList;
	
	@FXML
	private void initialize() {
		headerViewController.setTitle("REGISTER REPORT");
		registerList = FXCollections.observableArrayList();
		
		List<RegisterData> list = RegisterUtils.getAll();
		if(list != null) {
			registerList.addAll(list);
		}
		
		registerIdColumn.setCellValueFactory(new PropertyValueFactory<RegisterData, String>("RegisterId"));
		userIdColumn.setCellValueFactory(new PropertyValueFactory<RegisterData, Integer>("UserId"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<RegisterData, Date>("Date"));
		
		tableData.setItems(registerList);
	}
}
