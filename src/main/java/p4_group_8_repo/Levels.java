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


/**
 * <p>
 * Contains methods and constructors to start a level, key listeners, level progression detection, number positioning and word positioning
 * <br>
 * {@code Levels} class is instantiated inside the {@code MainMenu} class
 * </p>
 * 
 * <p>
 * Adding Levels:
 * New levels can be added by creating new classes by copying and pasting the template given</p>
 * 
 * <p>
 * Template:</p>
 * <pre><code>
 * public void lvl_x(Stage stage_x) throws Exception{
 * 		background.add(yourBackground);
 *		setScene(background, x, y);
 *		start(stage_x);
 * }</code></pre>
 * <p>
 * After pasting the template, replace x with a new level variable then add a new background image and obstacles/turtles/wet turtles/logs.
 * <br>
 * After adding a new level, the value of the finalLevel Int variable has to be incremented
 * </p>
 * 
 * <p>
 * An example for a level would be:
 * </p>
 * <pre><code>
 * public void lvl_9(Stage stage_9){
 *		BackgroundImage froggerBackground = new BackgroundImage( "frog background2.png" );
 *		background.add(froggerBackground);
 *		
 *		background.add(new Log( "log3.png", 150, 490, 329, 0.75));	
 *		background.add(new Obstacle( "car1Left.png", 500, 690, -6, 50, 50));
 *
 *		setScene(background, x, y);
 *		start(stage_9);
 * }</code></pre>
 * 
 * 
 * @author Pang CH
 *
 */
public class Levels extends Actor{
	//file path and icon image declaration
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private Image froggerIcon = new Image( img_path + "icon-frogger-pixel-512x512.png");
	private BackgroundImage pausemenu = new BackgroundImage( "pausemenu.png" );
	/**
	 * Instantiating a new Animal class with an image which represents the player controlled character
	 */
	public Animal animal = new Animal( img_path + "froggerUp.png");
	
	private EndMenu endMenu;
	private MyStage background;
	private Stage levelStage;
	private Scene scene;
	/**
	 * Declaring AnimationTimer class timer variable
	 */
	public AnimationTimer timer;
	
	//game window sizes
	/**
	 * Width variable of the game window size
	 */
	private int x = 600;
	/**
	 * Height variable of the game window size
	 */
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
	
	/**
	 * Int variable representing dimensions/size of the digit to be displayed
	 */
	public int digDim = 35;
	/**
	 * Int variable representing the amount of pixels needed as spacing in between digits
	 */
	public int digShift = 30;
	
	//player word variables
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
	/**
	 * Boolean variable representing if a word is needed to be changed
	 */
	public boolean changeWord = false;
	private boolean timerStarted = false;
	private boolean muteMusic = false;
	private boolean notified = false;
	
	private int resetEndValue = animal.getStopInt();
	
	public void act(long now) {
	}
	
	//change finalLevel when new level is added
	private int currLevel = 0;
	private int finalLevel = 3;
	
	/**
	 * The {@code checkLevel} method checks which level the player needs to progress to
	 * @param stage {@code Stage} class variable instance which represents the current state of the window
	 */
	public void checkLevel(Stage stage){
		newBackground();
		//notify new level
		Alert alert = new Alert(AlertType.INFORMATION);
		
		//prints current level number
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
			System.out.print("ERROR: Line 136: Can enter next level\n");
		}
	}
	
	/**
	 * Constructor method that is used to be added to a {@code MyStage} class instance
	 */
	public Levels() {
	}
	
	/**
	 * <p>
	 * Constructor method that updates the Stage instance in the {@code Levels} class when first instantiated and starts displaying levels
	 * <br>
	 * The {@code Levels(Stage stage)} constructor is usually called by the {@code MainMenu} class
	 * </p>
	 * @param stage {@code Stage} class instance that represents the current stage
	 */
	public Levels(Stage stage){
		this.levelStage = stage;
		try {
			checkLevel(stage);
		} catch (Exception e) {
			System.out.println("ERROR: Line 135: Cannot call checkLevel()");
			e.printStackTrace();
		}
		
		//shortcut keys
		setOnKeyPressed(new EventHandler<KeyEvent>() { 
			public void handle(KeyEvent event){
				if ( event.getCode() == KeyCode.M && !(animal.gamePaused) ) { // mute music
					muteLogic();
				}
				if ( event.getCode() == KeyCode.P ) { // pause level
					pauseLogic();
				}
				if ( event.getCode() == KeyCode.G ) {
					if( animal.getGodMode() ) {
						animal.setGodMode( false );
					}else {
						animal.setGodMode( true );						
					}
				}
				if( event.getCode() == KeyCode.L ) {
					animal.setStop(5);
				}
			}
		});
	}
	
	/**
	 * Level one of the game
	 * @param stage_1 Stage instance passed in from the {@code checkLevel} method
	 */
	public void lvl_1(Stage stage_1){
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
	
	/**
	 * Level two of the game
	 * @param stage_2 Stage instance passed in from the {@code checkLevel} method
	 */
	public void lvl_2(Stage stage_2){
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
	
	/**
	 * Level three of the game
	 * @param stage_3 Stage instance passed in by the {@code checkLevel} method
	 */
	public void lvl_3(Stage stage_3){
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
	
	/**
	 * Creates a timer for the {@code Levels} class
	 */
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
	
	/**
	 * Instantiates a new {@code Levels} class scene {@code Scene} class
	 * @param background A local {@code MyStage} class variable that has every graphical resource
	 * @param x Int variable that represents the width of the game window
	 * @param y Int variable that represents the height of the game window
	 */
	public void setScene(MyStage background, int x, int y) {
		scene = new Scene(background,x,y);
	}
	/**
	 * Displays the game window using the {@code Stage} class
	 * @param stage A {@code Stage } class that controls the displayed game window
	 */
	public void showStage(Stage stage) {
		stage.setResizable(false);
		
		stage.getIcons().add(froggerIcon);
		stage.setTitle(" Frogger ");
		
		stage.setScene(scene);
		stage.show();
		levelStage = stage;
	}
	
	
	/**
	 * Method that initializes everything before displaying the game window
	 * @param stage Stage instance that is used to display the stage later
	 */
	public void start(Stage stage) {
		//add end objectives
		int n = 13;
		for(int x=0 ; x<5; x++) {
			background.add( new End( n, 96));
			n += 128;
		}
		
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
		//puts words 'timer' and 'score' on top
		setWord("timer", 5, 40, 5);
		setWord("score", 5, 250, 5);
		background.add(new Icon( "pause.png", iconDim, playPauseX, playPauseY ));
		
		//create timer and show game window
		if( muteMusic ) {
			background.add(new Icon( "soundoff.png", iconDim, soundX, soundY ));
			background.playMusic();
			background.pauseMusic();
		}else {	
			background.add(new Icon( "soundon.png", iconDim, soundX, soundY ));
			background.playMusic();
		}
		
		createTimer();
		timer.start();
		background.start();
		
		showStage(stage);
    }
	/**
	 * Calls the stop() method and notifies the games that it should proceed to the nextlevel
	 */
	public void nextLevel(){
		animal.setStop( resetEndValue );
		stop();
		
		try {
			checkLevel( levelStage );
		} catch (Exception e) {
			System.out.print("Line 413: ERROR: Unable to enter next level\n");
			//e.printStackTrace();
		}
	}
	/**
	 * Stops all the timers and music
	 */
    public void stop() {
    	background.stopMusic();
    	
    	timer.stop();
    	background.stop();
    }
    
    
    /**
     * Assigns a new MyStage background instance
     */
    public void newBackground() { 
    	this.background = new MyStage();
    }
    /**
     * Sets the private {@code MyStage} class background variable instance to the parameter
     * @param background {@code MyStage} class instance to set the {@code MyStage} class background variable instance in the {@code Levels} class to
     */
    public void setBackgroundInstance(MyStage background) {
    	this.background = background;
    }
    /**
     * Gets the {@code Levels} class background variable instance
     * @return {@code MyStage} background instance from the {@code Levels} class
     */
    public MyStage getBackgroundInstance() {
    	return background;
    }
    
    
    /**
     * Displays the n Int variable parameter on the x and y Int variable parameters
     * @param n Int variable to be displayed on the game window
     * @param x Int variable of the x coordinate on the game window
     * @param y Int variable of the y coordinate on the game window
     */
    public void setNumber(int n, int x, int y) { //set digit sprites for score and timer
    	loopCount = 0;
    	while (n>0) {
    		int val = n%10;
    		n = n/10;
    		background.add(new Digit(val, digDim, x - (digShift*loopCount), y)); // display digits from left to right
    		loopCount += 1;
    	}
    }
    /**
     * Displays the string String parameter on the x and y variable parameters
     * @param string String variable to be displayed on the game window
     * @param maxWordLength Int variable that represents the maximum word length intended
     * @param x Int variable of the x coordinate on the game window
     * @param y Int variable of the y coordinate on the game window
     */
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
    
    /**
     * Declares a game over status and instantiates the {@code EndMenu} class
     */
    public void gameOver() {
    	stop();
    	if(!notified) {
    		notified = true;
    		newBackground();
    		endMenu = new EndMenu(levelStage);
    	}
    }
    
    /**
     * Checks and changes the boolean variable of the changeTimer boolean variable 
     * @return Boolean variable of the changeTimer boolean variable
     */
    public boolean changeTimer() {
		if (changeTimer) {
			changeTimer = false;
			return true;
		}
		return false;
	}
    /**
     * Checks and changes the boolean variable of the changeWord boolean variable
     * @return Boolean variable of the changeWord boolean variable
     */
    public boolean changeWord() {
		if (changeWord) {
			changeWord = false;
			return true;
		}
		return false;
	}
    
    /**
     * Converts nanoseconds to seconds
     * @param nnSec Long variable representing nanoseconds
     * @return Long variable that is converted from nanoseconds to seconds
     */
    private long nanoToSec(long nnSec) { 
    	return (nnSec/1000000000);
    }
    
    
    /**
     * Contains the game pausing and resuming mechanic
     */
    private void pauseLogic() {
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
    /**
     * Contains the mute and unmute game mechanic
     */
    private void muteLogic() {
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
}
