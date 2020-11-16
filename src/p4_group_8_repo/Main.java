package p4_group_8_repo;

import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Main extends Application {
	/*
	String img_path = new String("file:src/p4_group_8_repo/");
	AnimationTimer timer;
	
	//stage background and animal sprites
	MyStage background = new MyStage();
	Animal animal = new Animal( img_path + "froggerUp.png");*/
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Levels level = new Levels();
		level.lvl_1(stage);
		level.start(); 
		
	}
}
