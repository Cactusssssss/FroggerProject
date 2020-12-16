package p4_group_8_repo;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * {@code World} class contains key listeners, {@code Actor} class addition/deletion and timers
 * 
 * @author Pang CH
 *
 */
public abstract class World extends Pane {
    private AnimationTimer timer;
    
    /**
     * Constructor that contains key listeners
     */
    public World() {
    	sceneProperty().addListener(new ChangeListener<Scene>(){// key listeners
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) 
								getOnKeyReleased().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
						
					});
					
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) {
								getOnKeyPressed().handle(event);
							}
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
					});
				}
				
			}
    	
		});
    }
    
    /**
     * Timer method that creates a new timer for all added {@code actors} variables in the Actor list data structure
     */
    public void createTimer() {
        timer = new AnimationTimer() {
            public void handle(long now) {
                act(now);
                List<Actor> actors = getObjects(Actor.class);
                
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
            }
        };
    }
    
    /**
     * Starts a timer and calls the {@code createTimer()} method
     */
    public void start() {
    	createTimer();
        timer.start();
    }
    
    /**
     * Stops the timer
     */
    public void stop() {
        timer.stop();
    }
    
    /**
     * Adds an {@code Actor} class instance to the {@code Actor} class instance list inside the {@code World} class
     * @param actor {@code Actor} class variable for actors to be added
     */
    public void add(Actor actor) {
        getChildren().add(actor);
    }
    
    /**
     * Removes an {@code Actor} class instance to the {@code Actor} class instance list inside the {@code World} class
     * @param actor {@code Actor} class variable for actors to be removed
     */
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    /**
     * 
     * @param <A> Class that extends the {@code Actor} class
     * @param cls An object of the {@code Actor} class
     * @return An array list of {@code Actor} class objects
     */
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
            }
        }
        return someArray;
    }
    
    public abstract void act(long now);
}
