package verse.engine.exceptions;

@SuppressWarnings("serial")
public class NonExistentGameStateException extends Exception{

    public NonExistentGameStateException(String stateName) {
        super(stateName + " is not an existing GameState!");
    }
	
}
