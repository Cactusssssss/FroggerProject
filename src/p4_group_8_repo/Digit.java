package p4_group_8_repo;

import javafx.scene.image.Image;

public class Digit extends Actor{
	int dim;// dimension

	public void act(long now) {
	}
	
	public Digit(int num, int dim, int x, int y) {
		Image im1 = new Image("file:src/p4_group_8_repo/"+num+".png", dim, dim, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}
}
