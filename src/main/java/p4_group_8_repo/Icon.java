/**
 * Icon class contains methods to create small icon images
 * 
 * usage:
 * Icon icon = new Icon( "icon.png", int size, int x, int y );
 * e.g:
 * Icon froggerIcon = new Icon( "soundon.png", 25, 200, 400 );
 * 
 *
 */
package p4_group_8_repo;

import javafx.scene.image.Image;

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
