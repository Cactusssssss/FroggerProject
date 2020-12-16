package p4_group_8_repo;

import java.util.ArrayList;

import javafx.scene.image.Image;


/**
 * <p>
 * {@code WetTurtle} class contains methods and a constructor for wet turtle spawning, wet turtle movement and wet turtle animation controller
 * </p>
 * <p>Usage:</p>
 * <pre><code>WetTurtle wetturtle = new WetTurtle( int x, int y, int movementSpeed, int image-width, in image-height );</pre></code>
 * <p>e.g:</p>
 * <pre><code>WetTurtle wturtle = new WetTurtle(700, 376, -1, 130, 130);</pre></code>
 * 
 * @author Pang CH
 */
public class WetTurtle extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private ArrayList<String> wetTurtleImg = new ArrayList<String>();
	
	//wet turtle size
	private int width = 0;
	private int height = 0;
	
	//wet turtle spawned?
	private boolean wetTurtleSpawned = false;
	
	//wet turtle movement speed
	private int movementSpeed =0;
	//turtle animation speed(milliseconds)
	private long animationSpeed = 800;
	//turtle image array list index
	private int animationFrame = 0;
	//current time
	private long currTime = 0;
	//turtle sink status
	private boolean sunk = false;
	
	
	public void act(long now) {
		move(movementSpeed , 0);

		if( !wetTurtleSpawned ) {
			wetTurtleSpawned = true;
			currTime = now;
		}
		if( nnToMilli(now-currTime) >= animationSpeed ){
			setImage( new Image( wetTurtleImg.get( animationFrame ), width, height, true, true) );
			animationFrame++;
			currTime = now;
			sunk = false;
			if( animationFrame == 3 ) {
				sunk = true;
			}
			if( wetTurtleImg.size() == animationFrame) {
				animationFrame = 0;
			}
		}
			
		if (getX() > 600 && movementSpeed>0)
			setX(-200);
		if (getX() < -75 && movementSpeed<0)
			setX(600);
	}
	public WetTurtle(int xpos, int ypos, int s, int w, int h) {
		addAllWetTurtleImg();
		
		setX(xpos);
		setY(ypos);
		
		width = w;
		height = h;
		movementSpeed = s;
		
		setImage( new Image( wetTurtleImg.get(animationFrame), w, h, true, true) );
	}
	
	/**
	 * Adds all wet turtle images to the the private array list {@code turtleImg}
	 */
	private void addAllWetTurtleImg() {
		for(int x =1; x<4; x++) {
			wetTurtleImg.add( img_path + "TurtleAnimation" + x + "Wet.png");
		}
	}
	
	/**
	 * Converts nanoseconds into milliseconds
	 * @param nnSec Long variable that repreesnts nanoseconds
	 * @return Long variablet that represents milliseconds
	 */
	private long nnToMilli(long nnSec) {
		return (nnSec/1000000);
	}
	
	/**
	 * 
	 * @return Boolean value that represents if the turtle is sunk
	 */
	public boolean isSunk() {
		return sunk;
	}
}
