package p4_group_8_repo;

import javafx.scene.image.Image;

/**
 * <p>
 * {@code Word} class contains a constructor to display individual alphabet images from a preset directory
 * </p>
 * <p>Usage:</p>
 * <pre><code>Word word = new Word(String alphabet, int dimension, int x, int y);</pre></code>
 * <p>e.g:</p>
 * <pre><code>Word word1 = new Word("y", 25, 200, 300);</pre></code>
 * 
 * @author Pang CH
 */
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
