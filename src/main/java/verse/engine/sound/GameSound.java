package verse.engine.sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import verse.engine.utils.ErrorHandler;

/**
 * A Sound represents a type of sound within the game itself. This could be background music, a simply sound effect or even dialogue.
 * The purpose of a Sound is to just MODEL the sound given to it via the soundFilePath.  
 * 
 * @author Faiz Gull Chaudhry
 */
public class GameSound implements Runnable{
        
    private Clip soundClip;
    private boolean loop;
        
    public GameSound(String soundFilePath, boolean loop) {
        this.loadSound(soundFilePath);
        this.loop = loop;
                
        // Setup a Line Listener for the sound Clip
        this.soundClip.addLineListener(new LineListener() {

                public void update(LineEvent e) {
                    if (e.getType().equals(LineEvent.Type.STOP)) {
                        System.exit(0);
                    }
                }
            });
    }
        
    /**
     * Given a file path to the wanted sound it loads and readys it as a Clip so that it can be used.
     * 
     * @param soundFilePath -> The String path to the file containing the sound which wants to be loaded
     */
    private void loadSound(String soundFilePath) {	
        try {			
            // These 4 lines deal with resolving .mp3 issues
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            AudioFormat baseFormat = audioStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                                                        baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream resolvedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);
                        
            // Setup the desired player with the given song/effect
            this.soundClip = AudioSystem.getClip();
            this.soundClip.open(resolvedAudioStream); 
                        
        } catch (Exception e) {
            ErrorHandler.raiseError(String.format("Failed to load given sound file from path %s!", soundFilePath), e);
        }
    }
        
    /**
     * A Clip which was created using the sound file given by the file path provided when this Sound was initialized.
     * 
     * @return The loaded Clip from the sound file path given
     */
    public Clip getDirectClip() {
        return this.soundClip;
    }

    public void run() {	
        if (this.loop) {
            this.soundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            this.soundClip.start();
        }
    }

}
