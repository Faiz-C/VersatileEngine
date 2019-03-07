package verse.engine.utils;

import verse.engine.exceptions.NoSoundToStopException;
import verse.engine.exceptions.SoundNotFinishedException;
import verse.engine.sound.GameSound;

/**
 * The purpose of a SoundHandler is to maintain and manage one of the many sounds a game can have. These sounds include 
 * BGM (Background Music), sound effects, voice dialogue, etc...
 * 
 * @author Faiz Gull Chaudhry
 */
public class SoundHandler {

    private Thread soundThread;
    private GameSound sound;
        
    public SoundHandler(GameSound sound) {
        this.sound = sound;
    }
        
    /**
     * 
     * @return - The GameSound that this SoundHandler is dealing with
     */
    public GameSound getSound() {
        return this.sound;
    }
        
    /**
     * Plays the sound that the SoundHandler was made to handle. Sound is played in a separate thread so that
     * it doesn't cause problems with the current thread.
     * @throws SoundNotFinishedException 
     */
    public void playSound() throws SoundNotFinishedException {
        if (this.soundThread != null && this.soundThread.isAlive()) {
            throw new SoundNotFinishedException();
        }
                
        this.sound.getDirectClip().setMicrosecondPosition(0); // Reset to the beginning of the song
                
        this.soundThread = new Thread(sound);
        this.soundThread.start();
    }
        
    /**
     * Stops the sound that is being played in a separate thread by the SoundHandler. 
     * @throws NoSoundToStopException 
     */
    public void stopSound() throws NoSoundToStopException {
        if (this.soundThread == null) {
            throw new NoSoundToStopException();
        }
		
        this.soundThread.interrupt();
        this.sound.getDirectClip().stop();
		
    }
	
}
