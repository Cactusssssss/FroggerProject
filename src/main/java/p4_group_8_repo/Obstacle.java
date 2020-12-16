
package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Obstacle} class contains a constructor and method for obstacle image and movement
 * </p>
 * <p>
 * Usage:</p> 
 * <pre><code>Obstacle obstacle = new Obstacle( "obstacle.png", int x, int y, int speed, int image-width, int image-height);</pre></code>
 * <p>e.g:</p>
 * <pre><code>Obstacle truck1 = new Obstacle( "truck1Right.png", 0, 649, 1, 120, 120);</pre></code>
 *
 * @author Pang CH
 */
public class Obstacle extends Actor {
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private int speed;

	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}
	
	public Obstacle(String imageLink, int xpos, int ypos, int s, int w, int h) {
		setImage(new Image(img_path + imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
		speed = s;
	}

}
