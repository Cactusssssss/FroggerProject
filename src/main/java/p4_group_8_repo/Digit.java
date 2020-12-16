package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Digit} class contains a constructor to display individual number images from a preset directory
 * </p>
 * 
 * <p>
 * Usage:
 * </p>
 * <pre><code>Digit( int number, int dimensions, int x, int y);</pre></code>
 * <p>
 * e.g:
 * </p>
 * <pre><code>Digit(9, 25, 300, 300));</pre></code>
 * 
 *
 */
public class Digit extends Actor{

	public void act(long now) {
	}
	
	public Digit(int num, int dim, int x, int y) {
		Image image = new Image("file:src/main/java/p4_group_8_repo/"+num+".png", dim, dim, true, true);
		setImage(image);
		setX(x);
		setY(y);
	}
}
