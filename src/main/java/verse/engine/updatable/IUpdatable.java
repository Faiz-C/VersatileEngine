package verse.engine.updatable;

/**
 * To be Updatable the given object has to be able to update itself in some way. For example,
 * a Player Object could be Updatable since it could move when updated.
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IUpdatable {

    /**
     * Updates the Objects current state to a new state depending on the implementation.
     */
    public void update();
	
}
