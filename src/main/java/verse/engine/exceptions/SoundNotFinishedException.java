package verse.engine.exceptions;

@SuppressWarnings("serial")
public class SoundNotFinishedException extends Exception{
        
    public SoundNotFinishedException() {
        super("Previously started sound has not finished");
    }

}
