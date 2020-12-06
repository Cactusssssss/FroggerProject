/**
 * Background image class contains methods to create 600*800 pixel background image
 * 
 * Usage:
 * instantiate to create a background image instance
 * BackgroundImage background = new BackgroundImage( "frog background2.png" );
 *
 */
package p4_group_8_repo;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	public void act(long now) {
	}
	
	public BackgroundImage(String imageLink) {
		setImage(new Image(img_path + imageLink, 600, 800, true, true));
	}
}
