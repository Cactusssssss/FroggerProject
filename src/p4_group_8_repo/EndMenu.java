package p4_group_8_repo;

//io libraries
import java.io.*;
import java.util.*;

//javafx libraries
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EndMenu extends Actor{
	private String img_path = new String("file:src/p4_group_8_repo/");
	private Levels level = new Levels();
	private MyStage background;
	
	//variables for keyStroke detection
	private String keyStrokes = new String();
	private int wordCount = 0;
	private int maxWordLength = 10;
	
	//window x & y
	private int x = 600;
	private int y = 800;
	
	public void act(long now) {
	}
	
	private void setNewBackground() {
		level.newBackground();
		background = level.getBackgroundInstance();
		level.maxWordLength = this.maxWordLength;
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
		
		checkHighScore();
		
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
		//System.out.print("word count: " + wordCount +"\n");
		//System.out.print("     input: "+keyStrokes +"\n");
		
		level.inputWords = keyStrokes;
		level.changeWord = true;
		level.showStage(stage);
	}
	public int getMaxWordLength() {
		return maxWordLength;
	}
	
	
	public void checkHighScore() {
		//start only if inserting highscore is needed
		level.gamePaused = true;
		level.createTimer();
		level.timer.start();
				
		level.setWord("hiscores", 7, 175, 130);
		
		//writeFile();
		readFile();
	}
	private void writeFile(String string) {
		try {
			Formatter fileWriter = new Formatter(new File( System.getProperty("user.dir") + "\\highscores.txt"));
			
			fileWriter.format(string);
			
			fileWriter.close();
		} catch (FileNotFoundException e) {
			System.out.print("ERROR: Line 110: Unable to write file");
			//e.printStackTrace();
		}
	}
	private void readFile() {
		String playerData = null;
		level.animal.setPoints(999);
		int newPlayerScore = level.animal.getPoints();
		level.worDim = 30;
		level.wordShift = 25;
		level.digDim = 30;
		level.digShift = 25;
		
		for( int y =0; y<10; y++ ) {
			for( int x=0; x<4; x++ ) {
				background.add(new Digit( 0, 30, 470 - (25*x), 200 + (35*y) ) );
			}
		}
		
		try {
			Scanner fileReader = new Scanner(new File( System.getProperty("user.dir") + "\\highscores.txt"));
			
			int loopCount = 0;
			while( fileReader.hasNext() ) {
				String playerName = fileReader.next() ;
				String playerScore = fileReader.next() ;
				playerData += playerName + " " + playerScore+"\n";
				
				int score=Integer.parseInt(playerScore);
				
				//System.out.print( playerName + " " + playerScore + "\n");
				
				level.setWord(playerName, 10, 100, 200 + (35*loopCount));
				level.setNumber(score, 470, 200 + (35*loopCount));
				loopCount += 1;
			}
			System.out.print( newPlayerScore + "\n");
			fileReader.close();
			
        } catch (Exception e) {
        	System.out.print("ERROR: Line 141: Unable to read file");
            e.printStackTrace();
        }
	}
	
	
	private String deleteLastChar(String s)   {
		return s.substring(0, s.length() - 1);  
	} 
}
