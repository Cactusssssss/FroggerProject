/**
 * Word class contains a method that sets the image for any alphabet image
 * 
 * usage:
 * Word word = new Word(String alphabet, int dimension, int x, int y);
 * e.g:
 * Word wordy = new Word("y", 25, 200, 300);
 * 
 */
package p4_group_8_repo;

import javafx.scene.image.Image;

public class Word extends Actor{

	public void act(long now) {
	}
	
	public Word(String alpha, int dim, int x, int y) {
		Image image = new Image("file:src/main/java/p4_group_8_repo/"+alpha+".png", dim, dim, true, true);
		setImage(image);
		setX(x);
		setY(y);
	}
}
