package p4_group_8_repo;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * <p>
 * {@code Animal} class is an extension of actor class containing death logic, death animation, hitbox detection, movement and player positioning
 * <br>
 * The {@code Animal} class is usually instantiated by the {@code Levels} class, this is to create a player controlled charater for the player
 * </p>
 * 
 * <p>
 * Usage example:
 * </p>
 * <pre><code>
 * Animal animal = new Animal( img_path + "froggerUp.png");
 * </code></pre>
 * 
 * @author Pang CH
 *
 */
public class Animal extends Actor {
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private ArrayList<String> waterDeathImg = new ArrayList<String>();
	private ArrayList<String> carDeathImg = new ArrayList<String>();
	private Levels level;
	
	//movement and player spawning x & y values
	private double movementY = 13.3333333*2;
	private double movementX = 10.666666*2;
	private double spawnX = 300;
	private double spawnY = 759.796;
	
	//player points
	private int points = 0;
	//image size
	private int imgSize = 40;
	//animation speed (in milliseconds)
	private double animationSpeed = 200;
	//furthest position traveled
	private double farPos = 800;
	//current time
	private long currTime = 0;
	//index number of death image array list
	private int animationFrame = 0;
	//last alive player positions
	private double xPosLast = 0;
	private double yPosLast = 0;
	
	//boolean values
	private boolean carDeath = false;
	private boolean waterDeath = false;
	private boolean noMove = false;
	private boolean changeScore = false;
	private boolean animalSpawned = false;
	
	/**
	 * Boolean value that represents game paused status
	 */
	public boolean gamePaused = false;
	
	//for debugging
	private boolean godMode = false;
	private int end = 0;
	
	ArrayList<End> inter = new ArrayList<End>();
	/**
	 * Animal constructor contains key listeners and movement logic of a player
	 * @param imageLink A string variable that contains the name of an image file
	 */
	public Animal(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		Image imgW1 = new Image( img_path + "froggerUp.png", imgSize, imgSize, true, true);
		Image imgA1 = new Image( img_path + "froggerLeft.png", imgSize, imgSize, true, true);
		Image imgS1 = new Image( img_path + "froggerDown.png", imgSize, imgSize, true, true);
		Image imgD1 = new Image( img_path + "froggerRight.png", imgSize, imgSize, true, true);
		Image imgW2 = new Image( img_path + "froggerUpJump.png", imgSize, imgSize, true, true);
		Image imgA2 = new Image( img_path + "froggerLeftJump.png", imgSize, imgSize, true, true);
		Image imgS2 = new Image( img_path + "froggerDownJump.png", imgSize, imgSize, true, true);
		Image imgD2 = new Image( img_path + "froggerRightJump.png", imgSize, imgSize, true, true);
		//add all death images
		addAllWaterDeathImg();
		addAllCarDeathImg();
		//spawn player
		respawnAnimal();
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {//player movement logic
			public void handle(KeyEvent event){
				if( !gamePaused && !noMove ) {
					if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {	            	
						yPosLast = getY() - movementY;
		                
						move(0, -movementY);
		                setImage(imgW2);
		            }else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {	            	
		            	xPosLast = getX() - movementX;
		            	
		            	move(-movementX, 0);
		            	setImage(imgA2);
		            }else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {	            	
		            	yPosLast = getY() + movementY;
		            	
		            	move(0, movementY);
		            	setImage(imgS2);
		            }else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {          	
		            	xPosLast = getX() + movementX;
		            	
		            	move(movementX, 0);
		            	setImage(imgD2);
		            }
				}
			}
		});
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if( !gamePaused && !noMove ) {
					if ( (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP ) && yPosLast == getY() ) {
						if (getY() < farPos) {//scoring system
							changeScore = true;
							farPos = getY();
							points+=20;
						}
		                move(0, -movementY);
		                setImage(imgW1);
		            }
		            else if ( (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) ) {	            	
		            	move(-movementX, 0);
		            	setImage(imgA1);
		            }
		            else if ( (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) && yPosLast == getY() ) {	            	
		            	move(0, movementY);
		            	setImage(imgS1);
		            }
		            else if ( (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) ) {	            	
		            	move(movementX, 0);
		            	setImage(imgD1);
		            }
				}
			}
		});
	}
	
	/**
	 * A method that contains death animations, movement mechanics and spawning mechanic
	 * @param now A long variable representing system ticks
	 */
	public void act(long now) {
		if( !animalSpawned ) { // player has spawned
        	xPosLast = getX();
        	yPosLast = getY();
			animalSpawned = true;
			currTime = now;
		}
		if (getY()<0 || getY()>800) { // Y-movement limitation
			setY(spawnY);
		}
		if ( getX()<0 ) { // X-movement limitation
			move( movementX*27, 0 );
		}else if( getX()> 557 ) {
			move( -movementX*27, 0 );
		}
		
		if( !(godMode) ) { 
			//death animations
			if (carDeath) { // car death animation
				noMove = true;
				if( nanoToMilli(now-currTime) >= animationSpeed && animationFrame < carDeathImg.size() ) {
					setImage(new Image( carDeathImg.get( animationFrame ) , imgSize, imgSize, true, true));
					currTime = now;
					animationFrame++;
				}
				if( animationFrame == carDeathImg.size() ) {
					//respawn animal
					respawnAnimal();
					
					carDeath = false;
					noMove = false;
					
					animationFrame = 0;
					if (points>50) {
						points-=50;
						changeScore = true;
					}
				}
			}
			if (waterDeath) {
				noMove = true;
				if( nanoToMilli(now-currTime) >= animationSpeed && animationFrame < waterDeathImg.size() ) {
					setImage(new Image( waterDeathImg.get( animationFrame ) , imgSize, imgSize, true, true));
					currTime = now;
					animationFrame++;
				}
				if( animationFrame == waterDeathImg.size() ) {
					//respawn animal
					respawnAnimal();
					
					waterDeath = false;
					noMove = false;
					
					animationFrame = 0;
					if (points>50) {
						points-=50;
						changeScore = true;
					}
				}
			}
			// death logic
			if (getIntersectingObjects(Obstacle.class).size() >= 1) {
				carDeath = true;
			}
			
			if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
				if(getIntersectingObjects(Log.class).get(0).getLeft()) {
					move(-2,0);
				}
				else {
					move (.75,0);
				}
			}else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
				move(-1,0);
			}else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
				if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
					waterDeath = true;
				} else { move(-1,0); }
			}else if (getIntersectingObjects(End.class).size() >= 1) { //objective intersection detection
				inter = (ArrayList<End>) getIntersectingObjects(End.class);
				if (getIntersectingObjects(End.class).get(0).isActivated()) {
					end--;
					points-=50;
				}
				points+=50;
				changeScore = true;
				farPos=800;
				getIntersectingObjects(End.class).get(0).setEnd();
				end++;
				
				respawnAnimal();
			}else if (getY()<413){	
				waterDeath = true;
			}
			
		}else {
			if (getIntersectingObjects(End.class).size() >= 1) {
				inter = (ArrayList<End>) getIntersectingObjects(End.class);
				if (getIntersectingObjects(End.class).get(0).isActivated()) {
					end -= 1;
					points-=50;
				}
				points+=50;
				changeScore = true;
				farPos=800;
				getIntersectingObjects(End.class).get(0).setEnd();
				end += 1;
				
				respawnAnimal();
			}
		}
	}//end act method
	
	/**
	 * Gets boolean value of 'end' variable
	 * @return A boolean value of whether the variable 'end' is more or equal to 5
	 */
	public boolean getStopBool() {
		return (end>=5);
	}
	/**
	 * Gets int value of 'end' variable
	 * @return An int value of end
	 */
	public int getStopInt() {
		return end;
	}
	/**
	 * Sets the global 'end' variable to the parameter
	 * @param end An int variable that should not be more than 5
	 */
	public void setStop(int end) {
		this.end = end;
	}
	
	/**
	 * Gets the int value of the variable 'points'
	 * @return An int value of the variable 'points'
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Sets the int value of the variable 'points'
	 * @param points An int value that which represents the amount of points
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * Sets the boolean variable 'changeScore' to the opposite boolean value
	 * @return A boolean value
	 */
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
	/**
	 * Adds all frogger water death images as strings to waterDeathImg array list
	 */
	private void addAllWaterDeathImg() {
		for(int x=1; x<5; x++) {
			waterDeathImg.add( img_path+"waterdeath"+x+".png" );
		}
	}
	/**
	 * Adds all frogger car death imgaes as strings to the carDeathImg array list
	 */
	private void addAllCarDeathImg() {
		for(int x =1; x<4; x++) {
			carDeathImg.add( img_path+"cardeath"+x+".png" );
		}
	}
	
	/**
	 * Gets the boolean value of the private boolean variable godMode(debugging tool)
	 * @return Boolean variable that represents the mortality of the player character
	 */
	public boolean getGodMode() {
		return godMode;
	}
	/**
	 * Sets the private boolean variable godMode(debugging tool) inside the {@code Animal} class to the boolean parameter
	 * @param gm Boolean variable that represents the mortality of the player character
	 */
	public void setGodMode(boolean gm) {
		godMode = gm;
	}
	
	/**
	 * Converts nanoseconds to milliseconds
	 * @param nnSec Long variable representing nanoseconds
	 * @return Long variable that is converted from nanoseconds to milliseconds
	 */
	private long nanoToMilli(long nnSec) { 
    	return (nnSec/1000000);
    }
	
	/**
	 * Spawns/Respawns animal to the set {@code spawnX} and {@code spawnY} Double variables
	 */
	private void respawnAnimal() {
		setX(spawnX);
		setY(spawnY);
		setImage(new Image( img_path + "froggerUp.png", imgSize, imgSize, true, true));
	}
}
