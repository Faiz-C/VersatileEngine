package verse.engine.sound;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.FloatControl;

import verse.engine.utils.ErrorHandler;
import verse.engine.utils.SoundHandler;

/**
 * A very straight forward sound manager for the VersatileEngine which allows for adding, removing, starting
 * and stopping sounds. Also acts as a volume manager as well.
 * 
 * @author Faiz Chaudhry
 */
public class SoundManager {
        
    private Map<String, SoundHandler> soundMap;
        
    // A Reserved Sound for Background Music
    public static final String BGM = "BGM";
        
    public SoundManager() {
        this.soundMap = new HashMap<String, SoundHandler>();
    }
        
    /**
     * Creates and stores a SoundHandler for the GameSound given. Stored under
     * the name given. If the name given already exists, it's associated value
     * will be replaced with the new GameSound given.
     * 
     * @param soundName -> Wanted name of the GameSound given
     * @param sound -> Actual GameSound wanting to be added
     */
    public void addSound(String soundName, GameSound sound) {
        this.soundMap.put(soundName, new SoundHandler(sound));
    }
        
    /**
     * Removes the GameSound associated with the given name if it is being stored.
     * 
     * @param soundName -> Name of the GameSound that is to be removed
     */
    public void removeSound(String soundName) {
        this.soundMap.remove(soundName);
    }
        
    /**
     * Starts the given sound if it exists.
     * 
     * @param soundName -> Name of the sound wanting to be started
     */
    public void playSound(String soundName) {
        try {
            this.soundMap.get(soundName).playSound();
        } catch (Exception e) {
            ErrorHandler.raiseError("Could not start sound " + soundName, e);
        }
    }
        
    /**
     * Stops the given sound if it exists.
     * 
     * @param soundName -> Name of the sound wanting to be stopped
     */
    public void stopSound(String soundName) {
        try {
            this.soundMap.get(soundName).stopSound();
        } catch (Exception e) {
            ErrorHandler.raiseError("Could not stop sound " + soundName, e);
        }
    }
        
    /**
     * Adjusts the volume of the given Sound to the one provided.
     * 
     * @param soundName -> The name of the sound whose volume is to be adjusted
     * @param newVolume -> The new volume to be adjusted to
     */
    public void adjustVolume(String soundName, float newVolume) {
        GameSound sound = this.soundMap.get(soundName).getSound();

        FloatControl volumeControl = (FloatControl) sound.getDirectClip().getControl(FloatControl.Type.MASTER_GAIN);
		
        float dB = (float) (Math.log(newVolume) / Math.log(10.0) * 20.0);
        volumeControl.setValue(dB);
    }
}
