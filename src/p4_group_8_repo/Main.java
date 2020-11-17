package p4_group_8_repo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	Levels level = new Levels();
	Animal animal = new Animal(level);
	
	MainMenu menu;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		menu = new MainMenu(stage);
		//level.lvl_1(stage);
	}
}
