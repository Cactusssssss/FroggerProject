package p4_group_8_repo;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Animal extends Actor {
	private String img_path = new String("file:src/p4_group_8_repo/");
	
	//x & y values
	double movementY = 13.3333333*2;
	double movementX = 10.666666*2;
	double spawnX = 300;
	double spawnY = 733.13+movementY;
	
	//values
	int points = 0;
	int imgSize = 40;
	int death = 0;
	double w = 800; // width
	long mseconds = 1000;
	
	//boolean values
	private boolean second = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;
	boolean muteMusic = false;
	
	//for debugging
	private boolean godMode = true;
	private int end = 0;
	
	ArrayList<End> inter = new ArrayList<End>();
	public Animal(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(spawnX);
		setY(spawnY);
		Image imgW1 = new Image( img_path + "froggerUp.png", imgSize, imgSize, true, true);
		Image imgA1 = new Image( img_path + "froggerLeft.png", imgSize, imgSize, true, true);
		Image imgS1 = new Image( img_path + "froggerDown.png", imgSize, imgSize, true, true);
		Image imgD1 = new Image( img_path + "froggerRight.png", imgSize, imgSize, true, true);
		Image imgW2 = new Image( img_path + "froggerUpJump.png", imgSize, imgSize, true, true);
		Image imgA2 = new Image( img_path + "froggerLeftJump.png", imgSize, imgSize, true, true);
		Image imgS2 = new Image( img_path + "froggerDownJump.png", imgSize, imgSize, true, true);
		Image imgD2 = new Image( img_path + "froggerRightJump.png", imgSize, imgSize, true, true);
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
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
		});
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
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
		}); 
	}
	
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
					end--;
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
	
	
	public boolean getStopBool() {
		return (end==5);
	}
	public int getStopInt() {
		return end;
	}
	public void setStop(int end) {
		this.end = end;
	}
	
	
	public int getPoints() {
		return points;
	}
	
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
}
