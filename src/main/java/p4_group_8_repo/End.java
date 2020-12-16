package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code End} class contains a constructor to create goal areas with images
 * </p>
 * 
 * <p>
 * Usage:
 * </p>
 * <pre><code>End end = new End(int x, in y);</pre></code>
 * <p>
 * e.g:
 * </p>
 * <pre><code>End end1 = new End(13,96);</pre></code>
 *
 * @author Pang CH
 */
public class End extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	private boolean activated = false;

	public void act(long now) {
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image(img_path +"End.png", 60, 60, true, true));
	}
	
	/**
	 * Changes the status of an {@code End } class variable instance by image replacement
	 */
	public void setEnd() {
		setImage(new Image(img_path +"FrogEnd.png", 70, 70, true, true));
		activated = true;
	}
	
	/**
	 * Returns the boolean value of the private boolean variable
	 * @return Boolean value of {@code activated } variable belonging to the {@code End } class
	 */
	public boolean isActivated() {
		return activated;
	}
	

}
