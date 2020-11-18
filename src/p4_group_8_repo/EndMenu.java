package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EndMenu extends Actor{
	private String img_path = new String("file:src/p4_group_8_repo/");
	private Levels level = new Levels();
	private MyStage background;
	
	private int x = 600;
	private int y = 800;
	
	private void setNewBackground() {
		level.newBackground();
		background = level.getBackgroundInstance();
	}
	
	public EndMenu() {
		System.out.print("End Menu instance Created!"); //for debug
	}
	
	public EndMenu (Stage endMenu) {
		setNewBackground();
		BackgroundImage endMenuBackground = new BackgroundImage( img_path + "game-over-menu.png");
		background.add(endMenuBackground);
		//add end menu as part of stage
		background.add(this);
		
		level.setScene(background, x, y);
    	level.showStage(endMenu);
    	
    	setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if ( event.getCode() != KeyCode.UNDEFINED) {
					System.out.print(event.getCode()+"\n");
				}
				if ( event.getCode() == KeyCode.ESCAPE) {
					endMenu.close();
				}
			}
		});
	}

	public void act(long now) {
	}
}
