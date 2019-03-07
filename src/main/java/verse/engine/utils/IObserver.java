package verse.engine.utils;

/**
 * Part of the classic implementation of the Observer/Observable Design. This is a slightly adjusted Observer interface.
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IObserver {
        
    /**
     * Updates the Observer when the Observable Object is changed
     */
    public void update(Object... args);

}
