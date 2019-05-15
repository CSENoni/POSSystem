package application;
	
import com.sun.javafx.css.StyleManager;

import application.Model.POSUtils;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
			StyleManager.getInstance().addUserAgentStylesheet(getClass().getResource("application.css").toString());
			
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("./view/Login.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					POSUtils.cleanOngoingSale();
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
