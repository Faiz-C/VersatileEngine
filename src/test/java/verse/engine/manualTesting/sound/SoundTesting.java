package verse.engine.manualTesting.sound;

import verse.engine.sound.GameSound;
import verse.engine.sound.SoundManager;

public class SoundTesting {
        
    public static void main(String[] args) {
        SoundManager soundManager = new SoundManager();
                
        GameSound sound = new GameSound("src/test/resources/Thrill.mp3", true);
                
        soundManager.addSound(SoundManager.BGM, sound);
                
        soundManager.adjustVolume(SoundManager.BGM, 0.05f);
                
        soundManager.playSound(SoundManager.BGM);
                
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
