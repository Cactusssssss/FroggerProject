package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Log} class contains methods and constructors to display a moving log platform with a specified image
 * </p>
 * 
 * <p>
 * Usage:</p>
 * <pre><code>Log log= new Log( "log.png", int size, int x, int y, double speed);</pre></code>
 * <p>
 * e.g:</p>
 * <pre><code>Log log1 = new Log( "logs.png", 300, 0, 276, -2);</pre></code>
 * 
 *
 */
public class Log extends Actor {
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private double speed;
	
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}
	
	public Log(String imageLink, int size, int xpos, int ypos, double s) {
		setImage(new Image(img_path + imageLink, size, size, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}
	
	/**
	 * Gets the boolean value of whether the private double variable {@code speed} is less than 0 or not
	 * @return Boolean value of the private double variable {@code speed}
	 */
	public boolean getLeft() {
		return speed < 0;
	}
}
