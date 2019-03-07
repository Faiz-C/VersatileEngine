package verse.engine.exceptions;

@SuppressWarnings("serial")
public class NoSoundToStopException extends Exception{

    public NoSoundToStopException() {
        super("SoundHandler cannot stop sound which is not playing");
    }
	
}
