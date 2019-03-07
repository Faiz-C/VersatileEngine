package verse.engine.exceptions;

@SuppressWarnings("serial")
public class ActionNotTrackedException extends Exception{

    public ActionNotTrackedException(String actionName) {
        super(String.format("The requested action, %s, is not being tracked", actionName));
    }
	
}
