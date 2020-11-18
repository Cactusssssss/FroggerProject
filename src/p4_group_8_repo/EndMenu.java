package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EndMenu extends Actor{
	private String img_path = new String("file:src/p4_group_8_repo/");
	private Levels level = new Levels();
	private MyStage background;
	
	private void setNewBackground() {
		level.newBackground();
		background = level.getBackgroundInstance();
	}
	
	public EndMenu() {
		System.out.print("End Menu instance Created!"); //for debug
	}
	
	public EndMenu (Stage endMenu) {
		setNewBackground();
		BackgroundImage froggerMenuBackground = new BackgroundImage( img_path + "menu-image2.png");
		background.add(froggerMenuBackground);
		//add main menu as part of stage
		background.add(this);
		
		//no background.start because game has not started
		level.setScene(background, x, y);
    	level.showStage(mainMenu);
    	
    	setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if(dispMenu) {
					if ( event.getCode() == KeyCode.SPACE) {// press space to start(go to lvl_1)
						try {
				    		level.checkLevel(mainMenu);
							dispMenu = false;
							System.out.print("Continued!\n");// DEBUG: test continue
							return;
						} catch (Exception e) {
							System.out.print("ERROR: Unable to continue to Level 1\n");
							e.printStackTrace();
						}
					}
				}else {
					return;
				}
			}
		});
	}
}
