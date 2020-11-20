package p4_group_8_repo;

import javafx.scene.image.Image;

public class Word extends Actor{

	public void act(long now) {
	}
	
	public Word(String alpha, int dim, int x, int y) {
		Image image = new Image("file:src/p4_group_8_repo/"+alpha+".png", dim, dim, true, true);
		setImage(image);
		setX(x);
		setY(y);
	}
}
