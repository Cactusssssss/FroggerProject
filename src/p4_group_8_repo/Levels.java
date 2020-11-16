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

public class Levels {
	String img_path = new String("file:src/p4_group_8_repo/");
	MyStage background = new MyStage();
	Animal animal = new Animal( img_path + "froggerUp.png");
	AnimationTimer timer;
	
	//frogger player
	//Animal animal = new Animal( img_path + "froggerUp.png");
	
	//public void lvl_1(Stage stage_1, Animal animal, AnimationTimer timer) throws Exception{
	public void lvl_1(Stage stage_1) throws Exception{
			//application window
	    	Scene scene  = new Scene(background,600,800);
	    	
			//background image
			BackgroundImage froggerbackground = new BackgroundImage( img_path + "frog background2.png");
			background.add(froggerbackground);
				
			//logs
			background.add(new Log( img_path + "logs.png", 300, 0, 276, -2));
			background.add(new Log( img_path + "logs.png", 300, 400, 276, -2));
			background.add(new Log( img_path + "log3.png", 150, 0, 166, 0.75));
			background.add(new Log( img_path + "log3.png", 150, 220, 166, 0.75));
			background.add(new Log( img_path + "log3.png", 150, 440, 166, 0.75));
			background.add(new Log( img_path + "log3.png", 150, 50, 329, 0.75));
			background.add(new Log( img_path + "log3.png", 150, 270, 329, 0.75));
			background.add(new Log( img_path + "log3.png", 150, 490, 329, 0.75));

			//Turtles
			background.add(new Turtle(500, 376, -1, 130, 130));
			background.add(new Turtle(300, 376, -1, 130, 130));
			background.add(new WetTurtle(700, 376, -1, 130, 130));
			background.add(new WetTurtle(600, 217, -1, 130, 130));
			background.add(new WetTurtle(400, 217, -1, 130, 130));
			background.add(new WetTurtle(200, 217, -1, 130, 130));
				
			background.add(new End(13,96));
			background.add(new End(141,96));
			background.add(new End(141 + 141-13,96));
			background.add(new End(141 + 141-13+141-13+1,96));
			background.add(new End(141 + 141-13+141-13+141-13+3,96));
				
				
			//truck obstacles
			background.add(new Obstacle( img_path + "truck1"+"Right.png", 0, 649, 1, 120, 120));
			background.add(new Obstacle( img_path + "truck1"+"Right.png", 300, 649, 1, 120, 120));
			background.add(new Obstacle( img_path + "truck1"+"Right.png", 600, 649, 1, 120, 120));
			background.add(new Obstacle( img_path + "truck2Right.png", 0, 540, 1, 200, 200));
			background.add(new Obstacle( img_path + "truck2Right.png", 500, 540, 1, 200, 200));
				
			//car obstacles
			background.add(new Obstacle( img_path + "car1Left.png", 100, 597, -1, 50, 50));
			background.add(new Obstacle( img_path + "car1Left.png", 250, 597, -1, 50, 50));
			background.add(new Obstacle( img_path + "car1Left.png", 400, 597, -1, 50, 50));
			background.add(new Obstacle( img_path + "car1Left.png", 550, 597, -1, 50, 50));
			background.add(new Obstacle( img_path + "car1Left.png", 500, 490, -5, 50, 50));
			background.add(new Digit(0, 30, 360, 25));
			
			//frogger player
			background.add(animal);
			
			//show window
			background.start();
			stage_1.setScene(scene);
			stage_1.show();
	}
	public void lvl_2(Stage stage_2) throws Exception{
		//add new level here
	}
	
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	if (animal.getStop()) {
            		winPopup();
            	}
            }
        };
    }
	
	public void start() {
    	createTimer();
        timer.start();
        background.playMusic();
    }

    public void stop() {
        timer.stop();
        background.stopMusic();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		int d = n / 10;
    		int k = n - d * 10;
    		n = d;
    		background.add(new Digit(k, 30, 360 - shift, 25));
    		shift+=30;
    	}
    }
    
    public void winPopup() {
    	System.out.print("STOP: Player has won!");
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("You Have Won The Game!");
		alert.setHeaderText("Your High Score: "+ animal.getPoints()+"!");
		alert.setContentText("Highest Possible Score: 800");
		alert.show();
		stop();
    }
}
