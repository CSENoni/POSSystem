package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderController {
	@FXML
	private Label title;
	
	public void setTitle(String text) {
		title.setText(text);
	}
}
