package verse.engine.utils;

/**
 * Classic Implementation of the Observer/Observable Design
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IObserver {
        
    /**
     * Updates the Observer when the Observable Object is changed
     */
    public void update(Object... args);

}
