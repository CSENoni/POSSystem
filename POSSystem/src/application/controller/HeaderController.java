package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderController {
	@FXML
	private Label title;
	
	public void setTitle(String text) {
		title.setText(text);
	}
	
	public void toHome(ActionEvent event) throws IOException {
		POSUtils.cleanOngoingSale();
		POSUtils.changeScene(event, getClass(), "../view/Home.fxml");
	}
	
	public void toLogin(ActionEvent event) throws IOException {
		POSUtils.cleanOngoingSale();
		POSUtils.changeScene(event, getClass(), "../view/Login.fxml");
	}
}
