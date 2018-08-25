package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{


			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("BlackCoffee (Media Player) V-1.0.0");
			primaryStage.setScene(scene);
			primaryStage.show();

            primaryStage.setResizable(false);

	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
