package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code BackgroundImage} class contains methods to create a 600x800 pixel background
 * </p>
 * <p>
 * Usage:
 * </p>
 * <br>
 * <pre><code>BackgroundImage background = new BackgroundImage( "frog background2.png" );</code></pre>
 * 
 */
public class BackgroundImage extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	public void act(long now) {
	}
	
	public BackgroundImage(String imageLink) {
		setImage(new Image(img_path + imageLink, 600, 800, true, true));
	}
}
