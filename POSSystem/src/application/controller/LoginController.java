package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import application.Model.User.UserUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class LoginController {
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField userPassword;
	
	@FXML
	private Text errorInput;
	
	@FXML
	private void initialize() {
		// This is just for test. Remove it before presentation
		if(userName != null && userPassword != null) {
			userName.setText("tester1");
			userPassword.setText("12345");
		}
	}
	
	public void loginToHomeScreen(ActionEvent event) throws IOException {
		String name = userName.getText();
		String pass = userPassword.getText();
		
		if(isNotEmpty(name) && isNotEmpty(pass) && UserUtils.get(name, pass)) { 
			POSUtils.changeScene(event, getClass(), "../view/Home.fxml");
		}else {
			userName.setStyle("-fx-border-width: 3,3,3,3; -fx-border-color: red;");
			userPassword.setStyle("-fx-border-width: 3,3,3,3; -fx-border-color: red;");
			errorInput.setVisible(true);
		}
	}
	
	public void fieldFilling(KeyEvent event) {
		if(userName != null && userPassword != null) {
			userName.setStyle("");
			userPassword.setStyle("");
			errorInput.setVisible(false);
		}
	}
	
	private boolean isNotEmpty(String text) {
		return text != null && text.length() > 0;
	}
}
