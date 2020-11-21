package p4_group_8_repo;

import java.io.*;
import java.util.*;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

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
	int stopAt = -1;
	
	//window x & y
	private int x = 600;
	private int y = 800;
	
	//boolean values
	private boolean newHighScore = false;
	
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
				if( newHighScore ) {
					if( wordCount < maxWordLength ) {
						if ( event.getCode().isLetterKey() || event.getCode().isDigitKey() || event.getCode() == KeyCode.SPACE) {
							//register keystrokes and put into string
							wordCount += 1;
							keyStrokes = keyStrokes + event.getText();
							
							changeWord(endMenu);
						}
					}
					if( wordCount > 0) { //register backspace and remove last char from string
						if ( event.getCode() == KeyCode.BACK_SPACE ) { 
							wordCount -= 1;
							keyStrokes = deleteLastChar(keyStrokes);
							
							changeWord(endMenu);
						}
					}
					if ( event.getCode() == KeyCode.ENTER ) { // confirms the name entered
						confirmationAlert();
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
		level.animal.setPoints(11);// delete later
		int newPlayerScore = level.animal.getPoints();
		
		level.worDim = 30;
		level.wordShift = 25;
		level.digDim = 30;
		level.digShift = 25;
		
		for( int y =0; y<10; y++ ) { //display placeholder numbers
			for( int x=0; x<4; x++ ) {
				background.add(new Digit( 0, 30, 470 - (25*x), 200 + (35*y) ) );
			}
		}
		
		try { //display highscores from highscores.txt
			Scanner fileReader = new Scanner(new File( System.getProperty("user.dir") + "\\highscores.txt"));
			
			int loopCount = 0;
			while( loopCount < 10 ) {
				String playerName = fileReader.next() ;
				String playerScore = fileReader.next() ;
				int score=Integer.parseInt(playerScore);
				
				if( newPlayerScore >= score && newHighScore == false ) {
					stopAt = loopCount;
					newHighScore = true;
					level.wordY = 200 + (35*loopCount);
					
					background.add(new Word( "pointer", 30, 75, 200 + (35*loopCount) ) ); // add pointer indicator
					level.setNumber(newPlayerScore, 470, 200 + (35*loopCount));
					loopCount += 1;
					level.setWord(playerName, 10, 100, 200 + (35*loopCount));
					level.setNumber(score, 470, 200 + (35*loopCount));
					loopCount += 1;
				}else {
					level.setWord(playerName, 10, 100, 200 + (35*loopCount));
					level.setNumber(score, 470, 200 + (35*loopCount));
					loopCount += 1;
				}
			}//end while
			fileReader.close();
			if( newHighScore ) {
				newHighScoreAlert();
			}else {
				gameOverAlert();
			}
        } catch (Exception e) {
        	System.out.print("ERROR: Line 172: Unable to read file");
            e.printStackTrace();
        }
	}
	private void insertNewHiScore() {
		int newPlayerScore = level.animal.getPoints();
		String playerData = new String();
		
		try { //insert new high score into a string if 
			Scanner fileReader = new Scanner(new File( System.getProperty("user.dir") + "\\highscores.txt"));
			
			int loopCount = 0;
			while( loopCount < 10 ) {
				String playerName = fileReader.next() ;
				String playerScore = fileReader.next() ;
				
				int repeat = 0;
				if( stopAt == loopCount ) {
					if( newPlayerScore > 999 ) {
						repeat = 0;
					}else if( newPlayerScore > 99 ) {
						repeat = 1;
					}else if( newPlayerScore > 9 ) {
						repeat = 2;
					}else if( newPlayerScore < 10 ) {
						repeat = 3;
					}else {
						repeat = 4;
					}
					
					playerData += keyStrokes + " ";
					while( repeat > 0 ) {
						playerData += "0";	
						repeat -= 1;
					}
					playerData += String.valueOf(newPlayerScore)+"\n";
					loopCount += 1;
					playerData += playerName + " " + playerScore+"\n";
				}else {
					playerData += playerName + " " + playerScore+"\n";
				}
				
				loopCount += 1;
			}//end while
			
			fileReader.close();
			
		}catch (Exception e) {
        	System.out.print("ERROR: Line 218: Unable to read file");
            e.printStackTrace();
        }
		
		writeFile( playerData );
	}
	
	
	
	private void gameOverAlert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	
    	alert.setTitle("Game Over!");
    	alert.setHeaderText("You Did Not Get A New High Score!\nYour High Score: " + level.animal.getPoints());
    	alert.setContentText("Try Again Next Time!");
    	alert.show();
	}
	private void newHighScoreAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		
		alert.setTitle("New High Score!");
    	alert.setHeaderText("You Got A New High Score!\nYour High Score: " + level.animal.getPoints());
    	alert.setContentText("Enter Your Name With Alphabet Or Digit Keys.\nPress 'Enter' To Confirm Your Name Afterwards.");
    	alert.show();
	}
	private void confirmationAlert() {
		Alert confirm = new Alert( AlertType.CONFIRMATION, "You Will Not Be Able To Change Your Name\nAfter You Press 'OK'.");
		confirm.setHeaderText("Are You Sure?");
        confirm.setTitle("Confirm Name");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
            	keyStrokes = keyStrokes.replace(" ", "-");
				insertNewHiScore();
				newHighScore = false;
            }
        });
	}
	
	private String deleteLastChar(String s)   {
		return s.substring(0, s.length() - 1);  
	} 
}
