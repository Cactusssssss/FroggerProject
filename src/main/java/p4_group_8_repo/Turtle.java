package p4_group_8_repo;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Turtle} class contains methods and a constructor for turtle spawning, turtle movement and turtle animation controller
 * </p>
 * <p>
 * Usage:</p>
 * <pre><code>Turtle turtle = new Turtle( int x, int y, int speed, int image-width, in image-height );</pre></code>
 * <p>
 * e.g:</p>
 * <pre><code>Turtle turtle1 = new Turtle(500, 376, -1, 130, 130);</pre></code>
 * 
 * @author Pang CH
 * 
 */
public class Turtle extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private ArrayList<String> turtleImg = new ArrayList<String>();
	
	//turtle size
	private int width = 0;
	private int height = 0;
	
	//turtle spawned?
	private boolean turtleSpawned = false;
	
	//turtle movement speed
	private int movementSpeed = 0;
	//turtle animation speed(milliseconds)
	private long animationSpeed = 800;
	//turtle image array list index
	private int animationFrame = 0;
	//current time
	private long currTime = 0;
	
	
	public void act(long now) {
		move(movementSpeed , 0);
		
		if( !turtleSpawned ) {
			turtleSpawned = true;
			currTime = now;
		}
		if( nnToMilli(now-currTime) >= animationSpeed ){ //animation controller
			setImage( new Image( turtleImg.get( animationFrame ), width, height, true, true) );
			animationFrame++;
			currTime = now;
			if( turtleImg.size() == animationFrame) {
				animationFrame = 0;
			}
		}
		
		if (getX() > 600 && movementSpeed>0)
			setX(-200);
		if (getX() < -75 && movementSpeed<0)
			setX(600);
	}
	public Turtle(int xpos, int ypos, int s, int w, int h) {
		addAllTurtleImg();
		
		setX(xpos);
		setY(ypos);
		
		width = w;
		height = h;
		movementSpeed = s;
		
		setImage( new Image( turtleImg.get( animationFrame ), w, h, true, true) );
	}
	
	/**
	 * Adds all turtle images to the the private array list {@code turtleImg}
	 */
	private void addAllTurtleImg() {
		for(int x =1; x<4; x++) {
			turtleImg.add( img_path + "TurtleAnimation" + x + ".png");
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
}
