/**
 * digit class contains methods to display individual number sprites from a preset directory
 * 
 * usage:
 * Digit( int number, int dimensions, int x, int y);
 * e.g:
 * Digit(9, 25, 300, 300));
 * 
 *
 */
package p4_group_8_repo;

import javafx.scene.image.Image;

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
