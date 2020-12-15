package p4_group_8_repo;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Animal class is an extension of actor class containing death logic, death animation, hitbox detection, movement and player image
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
	
	int death = 0;
	
	// width
	private double w = 800;
	
	//boolean values
	private boolean second = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;
	boolean muteMusic = false;
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
		//set player spawning x & y
		setX(spawnX);
		setY(spawnY);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if( !gamePaused ) {
					if (noMove) {
					}else{
						if (second) {
							if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
				                move(0, -movementY);
				                changeScore = false;
				                setImage(imgW1);
				                second = false;
				            }else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {	            	
				            	move(-movementX, 0);
				            	setImage(imgA1);
				            	second = false;
				            }else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {	            	
				            	move(0, movementY);
				            	setImage(imgS1);
				            	second = false;
				            }else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {	            	
				            	move(movementX, 0);
				            	setImage(imgD1);
				            	second = false;
				            }
						}else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {	            	
			                move(0, -movementY);
			                setImage(imgW2);
			                second = true;
			            }else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {	            	
			            	move(-movementX, 0);
			            	setImage(imgA2);
			            	second = true;
			            }else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {	            	
			            	move(0, movementY);
			            	setImage(imgS2);
			            	second = true;
			            }else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {	            	
			            	move(movementX, 0);
			            	setImage(imgD2);
			            	second = true;
			            }
					}
				}
			}
		});
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if( !gamePaused ) {
					if (noMove) {
					}else {
					if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {	  
						if (getY() < w) {
							changeScore = true;
							w = getY();
							points+=10;
						}
		                move(0, -movementY);
		                setImage(imgW1);
		                second = false;
		            }
		            else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) {	            	
		            	move(-movementX, 0);
		            	setImage(imgA1);
		            	second = false;
		            }
		            else if (event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) {	            	
		            	move(0, movementY);
		            	setImage(imgS1);
		            	second = false;
		            }
		            else if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {	            	
		            	move(movementX, 0);
		            	setImage(imgD1);
		            	second = false;
		            }
					}
				}
			}
		});
	}
	
	/**
	 * A method that contains death animations and movement logic
	 * @param now A long variable representing system ticks
	 */
	public void act(long now) {
		if (getY()<0 || getY()>800) { // Y-movement limitation
			setX(spawnX);
			setY(spawnY);
		}
		if ( getX()<0 ) { // X-movement limitation
			move(movementX*27, 0);
		}else if( getX()> 557 ) {
			move(-movementX*27, 0);
		}
		if (carDeath) { // car death animation
			noMove = true;
			if ((now)% 11 == 0) {
				death++;
			}
			if (death==1) {
				setImage(new Image( img_path + "cardeath1.png", imgSize, imgSize, true, true));
			}
			if (death==2) {
				setImage(new Image( img_path + "cardeath2.png", imgSize, imgSize, true, true));
			}
			if (death==3) {
				setImage(new Image( img_path + "cardeath3.png", imgSize, imgSize, true, true));
			}
			if (death == 4) {
				setX(spawnX);
				setY(spawnY);
				carDeath = false;
				death = 0;
				setImage(new Image( img_path + "froggerUp.png", imgSize, imgSize, true, true));
				noMove = false;
				if (points>50) {
					points-=50;
					changeScore = true;
				}
			}
		}
		if (waterDeath) {
			noMove = true;
			if ( (now)% 11 == 0) { // water death animation
				death++;
			}
			if (death==1) {
				setImage(new Image( img_path + "waterdeath1.png", imgSize, imgSize , true, true));
			}
			if (death==2) {
				setImage(new Image( img_path + "waterdeath2.png", imgSize, imgSize , true, true));
			}
			if (death==3) {
				setImage(new Image( img_path + "waterdeath3.png", imgSize, imgSize , true, true));
			}
			if (death == 4) {
				setImage(new Image( img_path + "waterdeath4.png", imgSize, imgSize , true, true));
			}
			if (death == 5) {
				setX(spawnX);
				setY(spawnY);
				waterDeath = false;
				death = 0;
				setImage(new Image( img_path + "froggerUp.png", imgSize, imgSize, true, true));
				noMove = false;
				if (points>50) {
					points-=50;
					changeScore = true;
				}
			}
			
		}
		
		if( !(godMode) ) { 
			// death logic
			if (getX()>600) {
				move(-movementY*2, 0);
			}
			if (getIntersectingObjects(Obstacle.class).size() >= 1) {
				carDeath = true;
			}
			if (getX() == 240 && getY() == 82) {
				stop = true;
			}
			if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
				if(getIntersectingObjects(Log.class).get(0).getLeft()) {
					move(-2,0);
				}
				else {
					move (.75,0);
				}
			}
			else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
				move(-1,0);
			}
			else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
				if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
					waterDeath = true;
				} else { move(-1,0); }
			}
			else if (getIntersectingObjects(End.class).size() >= 1) { //objective intersection logic
				inter = (ArrayList<End>) getIntersectingObjects(End.class);
				if (getIntersectingObjects(End.class).get(0).isActivated()) {
					end--;
					points-=50;
				}
				points+=50;
				changeScore = true;
				w=800;
				getIntersectingObjects(End.class).get(0).setEnd();
				end++;
				setX(spawnX);
				setY(spawnY);
			}
			else if (getY()<413){	waterDeath = true;
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
				w=800;
				getIntersectingObjects(End.class).get(0).setEnd();
				end += 1;
				setX(spawnX);
				setY(spawnY);
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
}
