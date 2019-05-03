package application.Model;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class POSUtils {
	public static void changeScene(ActionEvent event, Class<?> className, String path) throws IOException {
		Parent view = FXMLLoader.load(className.getResource(path));
		Scene scene = new Scene(view);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
	}
}
