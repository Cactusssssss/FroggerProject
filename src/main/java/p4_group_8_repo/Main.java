/**
 * Main class starts the game by instantiating MainMenu()
 * 
 * version 0.6.1
 * 
 */
package p4_group_8_repo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	MainMenu menu;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		menu = new MainMenu(stage);
	}
}
