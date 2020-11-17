package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Levels extends World{
	private String img_path = new String("file:src/p4_group_8_repo/");
	private MyStage background;
	private Animal animal = new Animal( img_path + "froggerUp.png");
	private Image froggerIcon = new Image( img_path + "icon-frogger-pixel-512x512.png");
	
	//width value->x & height value->y
	private int x = 600;
	private int y = 800;
	
	//score number x & y value & dimensions
	private int scorex = 360;
	private int scorey = 50;
	private int scoredim = 25;
	
	AnimationTimer timer;
	Scene scene;
	
	//boolean values
	boolean changeTimerDigit = false;
	
	public void act(long now) {
	}

	public void lvl_1(Stage stage_1) throws Exception{
		//set new background & background image
		newBackground();
		BackgroundImage froggerBackground = new BackgroundImage( img_path + "frog background2.png");
		background.add(froggerBackground);
			
		//logs
		background.add(new Log( img_path + "logs.png", 300, 0, 276, -2));
		background.add(new Log( img_path + "logs.png", 300, 400, 276, -2));
		background.add(new Log( img_path + "log3.png", 150, 0, 166, 0.75));
		background.add(new Log( img_path + "log3.png", 150, 220, 166, 0.75));
		background.add(new Log( img_path + "log3.png", 150, 440, 166, 0.75));
		background.add(new Log( img_path + "log3.png", 150, 50, 329, 0.75));
		background.add(new Log( img_path + "log3.png", 150, 270, 329, 0.75));
		background.add(new Log( img_path + "log3.png", 150, 490, 329, 0.75));

		//turtles
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
		background.add(new Digit(0, scoredim, scorex, scorey));
		
		//frogger player
		background.add(animal);
		
		//show window
		setScene(background, x, y);
		showStage(stage_1);
		
		background.playMusic();
		start();
	}
	
	public void lvl_2(Stage stage_2) throws Exception{
		//add new level here
	}
	
	public void createTimer() {
		this.timer = new AnimationTimer() {
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
	
	public void showStage(Stage stage) {
		stage.setResizable(false);
		
		stage.getIcons().add(froggerIcon);
		stage.setTitle(" Frogger ");
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void setScene(MyStage background, int x, int y) {//set application window dimensions
		this.scene = new Scene(background,x,y);
	}
	
	
	public void start() {
		createTimer();
		timer.start();
		background.start();
    }

    public void stop() {
    	timer.stop();
    	background.stop();
    }
    
    public void newBackground() {
    	this.background = new MyStage();
    }
    
    public MyStage getBackgroundInstance() {
    	return this.background;
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		int d = n / 10;
    		int k = n - d * 10;
    		n = d;
    		background.add(new Digit(k, scoredim, scorex - shift, scorey));
    		shift+=30;
    	}
    }
    
    public void winPopup() {
    	System.out.print("STOP: Player has won!");
    	background.stopMusic();
    	stop();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Your High Score: " + animal.getPoints() + "!");
		alert.setContentText("Congratulations!!!");
		alert.show();
    }
    
    public boolean changeTimerDigit() {
		if (changeTimerDigit) {
			changeTimerDigit = false;
			return true;
		}
		return false;
	}
}
