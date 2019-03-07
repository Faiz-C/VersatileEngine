package verse.engine.exceptions;

/**
 * A simple Exception which indicates that you tried to stop a Sound which isn't playing at that moment. 
 * 
 * @author Faiz Chaudhry
 */
public class NoSoundToStopException extends Exception{
    public NoSoundToStopException() {
        super("SoundHandler cannot stop sound which is not playing");
    }
	
}
