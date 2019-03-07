package verse.engine.exceptions;

/**
 * An Exception to indicate that you are trying to start a given Sound again without being finished yet, it needs
 * to be stopped first.
 * 
 * @author Faiz Chaudhry
 */
public class SoundNotFinishedException extends Exception{
        
    public SoundNotFinishedException() {
        super("Previously started sound has not finished");
    }

}
