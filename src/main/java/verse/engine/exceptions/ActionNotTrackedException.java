package verse.engine.exceptions;

/**
 * A simple Exception which indicates that the action you are asking for is not actually being accounted for.
 * 
 * @author Faiz Chaudhry
 */
public class ActionNotTrackedException extends Exception{

    public ActionNotTrackedException(String actionName) {
        super(String.format("The requested action, %s, is not being tracked", actionName));
    }
	
}
