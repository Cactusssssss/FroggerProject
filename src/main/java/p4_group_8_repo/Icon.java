package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Icon} class contains a constructor to create small images to be added to the game window as icons
 * </p>
 * <p>
 * Usage:</p>
 * <pre><code>Icon icon = new Icon( "icon.png", int size, int x, int y );</pre></code>
 * <p>
 * e.g:</p>
 * <pre><code>Icon froggerIcon = new Icon( "soundon.png", 25, 200, 400 );</pre></code>
 * 
 * @author Pang CH
 */
public class Icon extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");

	public Icon(String imageLink, int size, int xpos, int ypos) {
		setImage(new Image(img_path + imageLink, size, size, true, true));
		setX(xpos);
		setY(ypos);
	}

	public void act(long now) {
	}
}
