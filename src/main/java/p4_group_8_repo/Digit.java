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
