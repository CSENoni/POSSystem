package application.controller;

import java.io.IOException;

import application.Model.POSUtils;
import javafx.event.ActionEvent;

public class LoginController {
	
	public void loginToHomeScreen(ActionEvent event) throws IOException {
		POSUtils.changeScene(event, getClass(), "../view/Home.fxml");
	}
}
