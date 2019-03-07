package verse.engine.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * An Implementation of the classic Observer/Observable pattern
 * 
 * @author Faiz Gull Chaudhry	
 */
public abstract class Observable {
        
    // A List of Observers that Observe this Observable Object
    private List<IObserver> observers = new ArrayList<IObserver>();
        
    /**
     * Adds an Observer
     * 
     * @param o - An Observer to add
     */
    public void addObserver(IObserver o) {
        observers.add(o);
    }
        
    /**
     * Removes an Observer
     * 
     * @param o - An Observer to remove
     */
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }
        
    /**
     * Updates all Observers
     */
    public void notifyObservers(Object... args) {
        for (IObserver o : observers) {
            o.update(args);
        }
    }

}
