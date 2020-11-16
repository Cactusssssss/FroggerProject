package p4_group_8_repo;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Levels level = new Levels();
		level.lvl_1(stage);
		level.start(); 
		
	}
}
