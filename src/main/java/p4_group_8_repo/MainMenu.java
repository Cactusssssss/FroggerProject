package p4_group_8_repo;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * <p>
 * MainMenu class contains methods and constructors of a main menu
 * </p>
 * <p>
 * Usage:</p>
 * <pre><code>MainMenu mm = new MainMenu(Stage stage)</code></pre>
 * 
 * 
 * @author Pang CH
 */
public class MainMenu extends Actor{
	private Levels level = new Levels();
	private MyStage background;
	
	private int x = 600;
	private int y = 800;
	
	private boolean dispMenu = true;
	
	private void setNewBackground() {
		level.newBackground();
		background = level.getBackgroundInstance();
	}
	
	
	/**
	 * Constrcutor for testing {@code MainMenu} class instantiation or to be added into a {@code MyStage}
	 */
	public MainMenu() {
		System.out.print("Main Menu instance Created!"); //for debug
	}
	
	/**
	 * Constructor for the {@code MainMenu} class that takes a parameter, the starts and displays the main menu
	 * @param mainMenu {@code Stage} class variable usually passed in from the {@code Main} 
	 */
	public MainMenu (Stage mainMenu) {
		//set new MyStage instance and add background image
		level.newBackground();
		setNewBackground();
		
		BackgroundImage mainMenuBackground = new BackgroundImage( "menu-image3.png" );
		background.add(mainMenuBackground);
		//add main menu as part of stage
		background.add(this);
		
		//no background.start because game has not started
		level.setScene(background, x, y);
    	level.showStage(mainMenu);
    	
    	setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if(dispMenu) {
					if ( event.getCode() == KeyCode.SPACE || event.getCode() == KeyCode.ENTER ) {// press space to start(go to lvl_1)
						try {
							dispMenu = false;
				    		level = new Levels(mainMenu);
							return;
						} 
						catch (Exception e) {
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
	
	/**
	 * Sets the private boolean variable {@code dispMenu} to the parameter boolean value
	 * @param dispMenu Boolean variable that represents if the main menu has already been displayed or not
	 */
	public void setDispMenu(boolean dispMenu) {
		this.dispMenu = dispMenu;
	}
	
	public void act(long now) {
	}
}
