package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField userPassword;
	
	
	public void loginToHomeScreen(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Home.fxml");
	}
}
