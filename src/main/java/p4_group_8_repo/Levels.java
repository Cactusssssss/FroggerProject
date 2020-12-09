/**
 * Levels class contains methods to start a level, shortcut keys( p and m ), level progression, set number and set words
 * Levels class is called from MainMenu class
 * 
 * usage:
 * new levels can be added by creating new classes by copying and pasting the template given
 * 
 * template:
 * public void lvl_x(Stage stage_x) throws Exception{
 *
 *		setScene(background, x, y);
 *		start(stage_x);
 * }
 * after pasting the template, replace x with a new level value then add a new background first then add new obstacles/turtles/wet turtles/logs
 * 
 * e.g:
 * public void lvl_9(Stage stage_9) throws Exception{
 *		BackgroundImage froggerBackground = new BackgroundImage( "frog background2.png" );
 *		background.add(froggerBackground);
 *		
 *		background.add(new Log( "log3.png", 150, 490, 329, 0.75));	
 *		background.add(new Obstacle( "car1Left.png", 500, 690, -6, 50, 50));
 *
 *		setScene(background, x, y);
 *		start(stage_9);
 * }
 * 
 *
 */
package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Levels extends Actor{
	//file path and icon image declaration
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private Image froggerIcon = new Image( img_path + "icon-frogger-pixel-512x512.png");
	private BackgroundImage pausemenu = new BackgroundImage( "pausemenu.png" );
	Animal animal = new Animal( img_path + "froggerUp.png");
	
	private EndMenu endMenu;
	private MyStage background;
	Stage levelStage;
	AnimationTimer timer;
	Scene scene;
	
	//game window width value->x & height value->y
	private int x = 600;
	private int y = 800;
	
	//score variables
	private int scorex = 360;
	private int scorey = 35;
	//timer variables
	private int timerx = 150;
	private int timery = 35;
	private long nowTimer = 0;
	private int timerSecs = 0;
	//
	private int loopCount = 0;
	int digDim = 35;
	int digShift = 30;
	
	//player keystroke variables
	String inputWords;
	int wordX = 100;
	int wordY = 550;
	int worDim = 35;
	int wordShift = 30;
	int maxWordLength = 0;
	
	//icon variables
	private int iconDim = 40;
	private int playPauseX = 550;
	private int playPauseY = 10;
	private int soundX = 500;
	private int soundY = 10;
	
	
	//boolean values
	private boolean changeTimer = false;
	boolean changeWord = false;
	private boolean timerStarted = false;
	private boolean muteMusic = false;
	private boolean notified = false;
	//for debugging use
	
	private int resetEndValue = animal.getStopInt();

	public void act(long now) {// not usable unless Levels created as an instance
	}
	
	//change finalLevel when new level is added
	private int currLevel = 0;
	private int finalLevel = 3;
	
	public void checkLevel(Stage stage) throws Exception{
		newBackground();
		//notify new level
		Alert alert = new Alert(AlertType.INFORMATION);
		
		System.out.print("level: "+ (currLevel+1) +"\n");
		alert.setHeaderText(" Level " + (currLevel+1) );
    	alert.show();
		
		//check and enter next level
		if (currLevel == 0 ) {
			lvl_1(stage);
		}else if( currLevel == 1 ) {
			lvl_2(stage);
		}else if( currLevel == 2 ){
			lvl_3(stage);
		}else {		
			System.out.print("ERROR: Line 123: Can enter next level\n");
		}
	}
	
	public Levels() {
	}
	
	public Levels(Stage stage){
		this.levelStage = stage;
		try {
			checkLevel(stage);
		} catch (Exception e) {
			System.out.print("ERROR: Line 135: Cannot call checkLevel()");
			//e.printStackTrace();
		}
		
		//shortcut keys
		setOnKeyPressed(new EventHandler<KeyEvent>() { 
			public void handle(KeyEvent event){
				if ( event.getCode() == KeyCode.M && !(animal.gamePaused) ) { // mute music
					if( muteMusic ) {
						background.add(new Icon( "soundon.png", iconDim, soundX, soundY ));
						
						background.resumeMusic();
						muteMusic = false;
					}else {
						background.add(new Icon( "soundoff.png", iconDim, soundX, soundY ));
						
						background.pauseMusic();
						muteMusic = true;
					}
				}
				if ( event.getCode() == KeyCode.P) { // pause level
					if( animal.gamePaused ) {
						background.remove(pausemenu);
						background.add(new Icon( "pause.png", iconDim, playPauseX, playPauseY ));
						
						background.resumeMusic();
						animal.gamePaused = false;
						
						background.add(new Icon( "soundon.png", iconDim, soundX, soundY ));
						timerStarted = false;
						createTimer();
						timer.start();
						background.start();
					}else {
						background.add(pausemenu);
						background.add(new Icon( "play.png", iconDim, playPauseX, playPauseY ));
						
						background.add(new Icon( "soundoff.png", iconDim, soundX, soundY ));
						background.pauseMusic();
						animal.gamePaused = true;
						
						timer.stop();
						background.stop();
					}
				}
			}
		});
	}
	
	//different levels
	public void lvl_1(Stage stage_1) throws Exception{
		//set new background instance & background image
		BackgroundImage froggerBackground = new BackgroundImage( "frog background2.png" );
		background.add(froggerBackground);
		
		//logs
		background.add(new Log( "logs.png", 300, 500, 376, 2));
		background.add(new Log( "logs.png", 300, 0, 276, -2));
		background.add(new Log( "logs.png", 300, 400, 276, -2));
		background.add(new Log( "logs.png", 300, 300, 217, 1.5));
		background.add(new Log( "log3.png", 150, 0, 166, 0.75));
		background.add(new Log( "log3.png", 150, 220, 166, 0.75));
		background.add(new Log( "log3.png", 150, 440, 166, 0.75));
		background.add(new Log( "log3.png", 150, 50, 329, 0.75));
		background.add(new Log( "log3.png", 150, 270, 329, 0.75));
		background.add(new Log( "log3.png", 150, 490, 329, 0.75));
		
		//car obstacles
		background.add(new Obstacle( "car1Left.png", 500, 690, -6, 50, 50));
		background.add(new Obstacle( "car1Right.png", 310, 640, 6, 50, 50));
		background.add(new Obstacle( "car1Left.png", 100, 597, -6, 50, 50));
		background.add(new Obstacle( "car1Right.png", 200, 540, 6, 50, 50));
		background.add(new Obstacle( "car1Left.png", 300, 490, -6, 50, 50));
		
		//set & start game (compulsory)
		setScene(background, x, y);
		start(stage_1);
	}
	
	public void lvl_2(Stage stage_2) throws Exception{
		//set new background instance & background image
		BackgroundImage froggerBackground = new BackgroundImage( "frog background2.png" );
		background.add(froggerBackground);
		
		//logs
		background.add(new Log( "logs.png", 300, 0, 276, -2));
		background.add(new Log( "logs.png", 300, 400, 276, -2));
		background.add(new Log( "log3.png", 150, 0, 166, 0.75));
		background.add(new Log( "log3.png", 150, 220, 166, 0.75));
		background.add(new Log( "log3.png", 150, 440, 166, 0.75));
		background.add(new Log( "log3.png", 150, 50, 329, 0.75));
		background.add(new Log( "log3.png", 150, 270, 329, 0.75));
		background.add(new Log( "log3.png", 150, 490, 329, 0.75));

		//turtles
		background.add(new Turtle(500, 376, -1, 130, 130));
		background.add(new Turtle(300, 376, -1, 130, 130));
		background.add(new WetTurtle(700, 376, -1, 130, 130));
		background.add(new WetTurtle(600, 217, -1, 130, 130));
		background.add(new WetTurtle(400, 217, -1, 130, 130));
		background.add(new WetTurtle(200, 217, -1, 130, 130));
		
		//truck obstacles
		background.add(new Obstacle( "truck1Right.png", 0, 649, 1, 120, 120));
		background.add(new Obstacle( "truck1Right.png", 300, 649, 1, 120, 120));
		background.add(new Obstacle( "truck1Right.png", 600, 649, 1, 120, 120));
		background.add(new Obstacle( "truck2Right.png", 0, 540, 1, 200, 200));
		background.add(new Obstacle( "truck2Right.png", 500, 540, 1, 200, 200));
			
		//car obstacles
		background.add(new Obstacle( "car1Left.png", 100, 597, -1, 50, 50));
		background.add(new Obstacle( "car1Left.png", 250, 597, -1, 50, 50));
		background.add(new Obstacle( "car1Left.png", 400, 597, -1, 50, 50));
		background.add(new Obstacle( "car1Left.png", 550, 597, -1, 50, 50));
		background.add(new Obstacle( "car1Left.png", 500, 490, -5, 50, 50));
		
		//set & start game (compulsory)
		setScene(background, x, y);
		start(stage_2);
	}
	
	public void lvl_3(Stage stage_3) throws Exception{
		//set new background instance & background image
		BackgroundImage froggerBackground = new BackgroundImage( "frog background2.png" );
		background.add(froggerBackground);
		
		//wet turtles
		background.add(new WetTurtle(700, 176, -1, 130, 130));
		background.add(new WetTurtle(500, 176, -1, 130, 130));
		background.add(new Turtle(300, 176, -1, 130, 130));
		background.add(new WetTurtle(700, 226, 1, 130, 130));
		background.add(new WetTurtle(400, 226, 1, 130, 130));
		background.add(new Turtle(200, 226, 1, 130, 130));
		background.add(new WetTurtle(700, 326, 1, 130, 130));
		background.add(new WetTurtle(400, 326, 1, 130, 130));
		background.add(new Turtle(200, 326, 1, 130, 130));
		background.add(new WetTurtle(700, 376, -1, 130, 130));
		background.add(new WetTurtle(500, 376, -1, 130, 130));
		background.add(new Turtle(300, 376, -1, 130, 130));
		
		//logs
		background.add(new Log( "logs.png", 300, 750, 276, -1));
		background.add(new Log( "logs.png", 300, 400, 276, -1));
		background.add(new Log( "logs.png", 300, 50, 276, -1));
		
		//obstacles
		background.add(new Obstacle( "truck2Left.png", 300, 490, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 80, 490, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 400, 543, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 180, 543, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 500, 590, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 280, 590, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 600, 643, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 380, 643, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 700, 690, -2, 200, 200));
		background.add(new Obstacle( "truck2Left.png", 480, 690, -2, 200, 200));
			
		//set & start game (compulsory)
		setScene(background, x, y);
		start(stage_3);
	}
	
	
	public void lvl_x(Stage stage_x) throws Exception{ //change x in level_x and stage_x to number
		//create new level here
		
		//set & start game (compulsory)
		setScene(background, x, y);
		start(stage_x);
	}

	
	
	public void createTimer() {
		this.timer = new AnimationTimer() {
            public void handle(long now) {
            	if( !animal.gamePaused ) {
	            	if( !timerStarted ) { //tells the program that the timer started
	            		nowTimer = now;
	            		timerStarted = true;
	            	}
					if( nanoToSec(now-nowTimer) == 1 ) { // check if 1 second has passed
						nowTimer = now;
						timerSecs += 1;
						changeTimer = true;
					}
	            	if ( animal.changeScore() ) {
	            		setNumber(animal.getPoints(), scorex, scorey);
	            	}
	            	if ( changeTimer() ) {
	            		setNumber(timerSecs, timerx, timery);
	            	}
	            	if (animal.getStopBool() && (currLevel == finalLevel) ) { //get current stage win boolean
	            		gameOver();
	            	}else if( animal.getStopBool() ){
	            		try {
							nextLevel();
						} catch (Exception e) {
							System.out.print("ERROR: Line 333:  Unable to call nextlevel()\n");
							//e.printStackTrace();
						}
	            	}
            	}// end if
            	if ( changeWord() ) {
            		setWord( inputWords, maxWordLength, wordX, wordY);
            	}
            }
        };
    }
	
	public void setScene(MyStage background, int x, int y) {//set application window dimensions
		scene = new Scene(background,x,y);
	}
	public void showStage(Stage stage) {
		stage.setResizable(false);
		
		stage.getIcons().add(froggerIcon);
		stage.setTitle(" Frogger ");
		
		stage.setScene(scene);
		stage.show();
		levelStage = stage;
	}
	
	
	//start, next and stop levels
	public void start(Stage stage) {
		//add objectives
		background.add(new End(13,96));
		background.add(new End(141,96));
		background.add(new End(141 + 141-13, 96));
		background.add(new End(141 + 141-13+141-13+1, 96));
		background.add(new End(141 + 141-13+141-13+141-13+3, 96));
		
		//add Level & animal to background
		background.add(animal);
		background.add(this);
		currLevel += 1;
		
		//print placeholder score and words
		loopCount = 0;
		while( loopCount < 4 ) {
			background.add(new Digit(0, digDim, scorex - (digShift*loopCount), scorey));
			background.add(new Digit(0, digDim, timerx - (digShift*loopCount), timery));
			loopCount += 1 ;
		}
		setWord("timer", 5, 40, 5);
		setWord("score", 5, 250, 5);
		background.add(new Icon( "pause.png", iconDim, playPauseX, playPauseY ));
		background.add(new Icon( "soundon.png", iconDim, soundX, soundY ));
		
		//create timer and show game window
		background.playMusic();
		
		createTimer();
		timer.start();
		background.start();
		
		showStage(stage);
    }
	public void nextLevel(){
		animal.setStop( resetEndValue );
		stop();
		
		try {
			checkLevel( levelStage );
		} catch (Exception e) {
			System.out.print("Line 402: ERROR: Unable to enter next level\n");
			//e.printStackTrace();
		}
	}
    public void stop() {
    	background.stopMusic();
    	
    	timer.stop();
    	background.stop();
    }
    
    
    // create new background instance
    public void newBackground() { 
    	this.background = new MyStage();
    }
    public void setBackgroundInstance(MyStage background) {
    	this.background = background;
    }
    public MyStage getBackgroundInstance() {
    	return background;
    }
    
    
    //change numbers and words
    public void setNumber(int n, int x, int y) { //set digit sprites for score and timer
    	loopCount = 0;
    	while (n>0) {
    		int val = n%10;
    		n = n/10;
    		background.add(new Digit(val, digDim, x - (digShift*loopCount), y)); // display digits from left to right
    		loopCount += 1;
    	}
    }
    public void setWord(String string, int maxWordLength, int x, int y) { //set digit sprites for score and timer
    	int count = 0;
    	while ( count < string.length() ) {
    		background.add( new Word(string.substring(count, count+1), worDim, x + (wordShift*count), y) ); // display digits from left to right
    		count += 1;
    	}
    	while ( count < maxWordLength ) {
    		background.add( new Word( "-" , worDim, x + (wordShift*count), y) );
    		count += 1;
    	}
    }
    
    // goes to end menu screen
    public void gameOver() {
    	stop();
    	if(!notified) {
    		notified = true;
    		newBackground();
    		endMenu = new EndMenu(levelStage);
    	}
    }
    
    //
    public boolean changeTimer() {
		if (changeTimer) {
			changeTimer = false;
			return true;
		}
		return false;
	}
    public boolean changeWord() {
		if (changeWord) {
			changeWord = false;
			return true;
		}
		return false;
	}
    
    //convert nanoseconds to seconds
    public long nanoToSec(long nnSec) { 
    	return (nnSec/1000000000);
    }
}
