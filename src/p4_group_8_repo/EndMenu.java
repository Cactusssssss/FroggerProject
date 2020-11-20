package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EndMenu extends Actor{
	private String img_path = new String("file:src/p4_group_8_repo/");
	private Levels level = new Levels();
	private MyStage background;
	private Stage gameEnd;
	
	//variables for keyStroke detection
	private String keyStrokes = new String();
	private int wordCount = 0;
	private int maxWordLength = 10;
	//printing word on screen
	private int loopCount = 0;
	private int worDim = 30;
	private int shift = 0;
	
	//window x & y
	private int x = 600;
	private int y = 800;
	
	//word starting location
	private int wordX = 30;
	private int wordY = 329;
	
	public void act(long now) {
	}
	
	private void setNewBackground() {
		level.newBackground();
		background = level.getBackgroundInstance();
	}
	
	public EndMenu() {
		System.out.print("End Menu instance Created!"); //for debug
	}
	
	public EndMenu (Stage endMenu) {
		setNewBackground();
		BackgroundImage endMenuBackground = new BackgroundImage( img_path + "game-over-menu.png");
		background.add(endMenuBackground);
		//add end menu as part of stage
		background.add(this);
		
		level.gamePaused = true;
		level.createTimer();
		level.timer.start();
		
		level.setScene(background, x, y);
    	level.showStage(endMenu);
    	
    	setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if( wordCount < maxWordLength ) {
					if ( event.getCode().isLetterKey() || event.getCode().isDigitKey() || event.getCode() == KeyCode.SPACE) {
						//register keystrokes and put into string
						wordCount += 1;
						keyStrokes = keyStrokes + event.getText();
						
						changeWord(endMenu);
					}
				}
				if( wordCount > 0) {
					if ( event.getCode() == KeyCode.BACK_SPACE ) { //register backspace and remove last char from string
						wordCount -= 1;
						keyStrokes = deleteLastChar(keyStrokes);
						
						changeWord(endMenu);
					}
				}
				if ( event.getCode() == KeyCode.ESCAPE) { //close game window
					endMenu.close();
				}
			}
		}); 
	}
	
	public void changeWord(Stage stage) {
		System.out.print("word count: " + wordCount +"\n");
		System.out.print("     input: "+keyStrokes +"\n");
		
		level.inputWords = keyStrokes;
		level.changeWord = true;
		level.showStage(stage);
	}
	
	private String deleteLastChar(String s)   {
		return s.substring(0, s.length() - 1);  
	} 
}
