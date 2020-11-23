package p4_group_8_repo;

import javafx.scene.image.Image;

public class End extends Actor{
	private String img_path = new String("file:src/main/java/p4_group_8_repo/");
	boolean activated = false;

	public void act(long now) {
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image(img_path +"End.png", 60, 60, true, true));
	}
	
	public void setEnd() {
		setImage(new Image(img_path +"FrogEnd.png", 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
