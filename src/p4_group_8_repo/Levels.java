package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Levels extends World{
	//file path and icon image declaration
	private String img_path = new String("file:src/p4_group_8_repo/");
	private MyStage background;
	private Animal animal = new Animal( img_path + "froggerUp.png");
	private Image froggerIcon = new Image( img_path + "icon-frogger-pixel-512x512.png");
	
	//width value->x & height value->y
	private int x = 600;
	private int y = 800;
	
	//x & y valeus
	private int scorex = 360;
	private int scorey = 50;
	private int timerx = 150;
	private int timery = 50;
	private long nowTimer = 0;
	private int timerSecs = 0;
	//digit dimensions
	private int digDim = 30;
	
	AnimationTimer timer;
	Scene scene;
	
	//boolean values
	private boolean changeTimer = false;
	private boolean timerStarted = false;

	public void act(long now) {// not usable unless Levels created as an instance
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
		background.add(new Digit(0, digDim, scorex, scorey));
		background.add(new Digit(0, digDim, timerx, timery));

		//frogger player
		background.add(animal);
		
		//set & start game
		setScene(background, x, y);
		start(stage_1);
	}
	
	public void lvl_2(Stage stage_2) throws Exception{
		//add new level here
	}
	
	public void createTimer() {
		this.timer = new AnimationTimer() {
            public void handle(long now) {
            	if( !timerStarted ) { //indicates the program that the timer started
            		nowTimer = now;
            		timerStarted = true;
            		System.out.print("second: " + nowTimer);
            	}
				if( (now-nowTimer)/1000000000 == 1 ) { // check if 1 second has passed
					nowTimer = now;
					timerSecs += 1;
					changeTimer = true;
				}
            	if (animal.changeScore()) { // change displayed points
            		setNumber(animal.getPoints(), scorex, scorey);
            	}
            	if ( changeTimer() ) { //change timer number
            		setNumber(timerSecs, timerx, timery);
            	}
            	if (animal.getStop()) { //get win condition
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
	
	
	public void start(Stage stage) { //starts AnimationTimer and displays game
		//background.playMusic();
		
		createTimer();
		this.timer.start();
		background.start();
		
		showStage(stage);
    }

    public void stop() {
    	//background.stopMusic();
    	
    	this.timer.stop();
    	background.stop();    	
    }
    
    public void newBackground() { // create new background for every stage
    	this.background = new MyStage();
    }
    public MyStage getBackgroundInstance() {
    	return this.background;
    }
    
    
    public void setNumber(int n, int x, int y) { //set digit sprites for score and timer
    	int shift = 0;
    	while (n>0) {
    		int val = n%10;
    		n = n/10;
    		background.add(new Digit(val, digDim, x - shift, y)); // display digits from left to right
    		shift += 30;
    	}
    }
    
    
    public void winPopup() {
    	System.out.print("STOP: Player has won!");
    	stop();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Your High Score: " + animal.getPoints() + "!");
		alert.setContentText("Congratulations!!!");
		alert.show();
    }
    
    public boolean changeTimer() {
		if (changeTimer) {
			changeTimer = false;
			return true;
		}
		return false;
	}
    
    public int nanoToSec(long nnSec) { //convert nanoseconds to seconds
    	int sec = (int)(nnSec/1000000000);
    	return sec;
    }

}
