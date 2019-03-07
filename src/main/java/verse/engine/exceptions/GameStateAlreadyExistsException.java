package verse.engine.exceptions;

@SuppressWarnings("serial")
public class GameStateAlreadyExistsException extends Exception{

    public GameStateAlreadyExistsException(String stateName) {
        super(stateName + " is already tied to an existing gameState");
    }
	
}
