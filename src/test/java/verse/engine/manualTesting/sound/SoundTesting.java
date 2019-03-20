package verse.engine.manualTesting.sound;

import verse.engine.sound.GameSound;
import verse.engine.sound.SoundManager;

public class SoundTesting {
        
    public static void main(String[] args) {
        SoundManager soundManager = new SoundManager();
                
        GameSound sound = new GameSound("src/test/resources/Thrill.mp3", true);
                
        soundManager.addSound(SoundManager.BGM, sound);
                
        soundManager.adjustVolume(SoundManager.BGM, 0.5f);
                
        soundManager.playSound(SoundManager.BGM);

        System.out.println("Starting To Play a Sound, then sleeping");
                
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished Playing the Sound!!");
    }

}
