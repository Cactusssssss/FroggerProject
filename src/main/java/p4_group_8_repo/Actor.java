package p4_group_8_repo;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Abstract {@code Actor } class contains intersection logic and timer, this class is used as an extension for other classes
 * 
 * @author Pang CH
 *
 */
public abstract class Actor extends ImageView{
	
	/**
	 * Sets the x and y coordinates of an {@code Actor } class instance
	 * @param dx Double variable that represents the x movement an {@code Actor } class instance
	 * @param dy Double variable that represents the y movement an {@code Actor } class instance
	 */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
    
    /**
     * Gets the parent class of the current class
     * @return A parent class of the current class
     */
    public World getWorld() {
        return (World) getParent();
    }
    
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }
    
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }
    
    /**
     * An array list for inserting and getting objects that are able to intersect with each other
     * @param <A> Array list data structure that extends actor class
     * @param cls An actor class for the array list
     * @return An array of objects
     */
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
    /**
     * An array list for inserting and getting objects that are able to intersect with each other
     * @param <A> Array list data structure that extends actor class
     * @param cls An actor class for the array list
     * @return First index of the array of objects
     */
    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
                break;
            }
        }
        return someArray.get(0);
    }
    
    /**
     * An abstract method that is able to update with system ticks
     * @param now A long variable that represents system ticks
     */
    public abstract void act(long now);
}
