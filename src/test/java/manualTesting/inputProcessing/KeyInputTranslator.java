package manualTesting.inputProcessing;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import verse.engine.inputProcessing.IInputTranslator;

/**
 * A simply version of an InputTranslator geared towards dealing with keyboard inputs (as 
 * KeyEvent.keyCodes) and translating binded ones to GameActions when requested to translate
 * input.
 * 
 * @author Faiz Gull Chaudhry
 */
public class KeyInputTranslator implements IInputTranslator {

    private Map<Integer, String> keyBinds; // Utilizing a Many-to-One mapping system
    public static String UP = "up", DOWN = "down", LEFT = "left", RIGHT = "right";
        
        
    public KeyInputTranslator() {
        this.loadDefaultKeyBinds();
    }
        
    public KeyInputTranslator(Map<Integer, String> keyBinds) {
        this.keyBinds = keyBinds;
    }
        
    /**
     * Simply loads a default set of key binds. This can be altered to suit the games
     * default control layout if it uses the keyboard.
     */
    private void loadDefaultKeyBinds() {
        this.keyBinds = new HashMap<Integer, String>();
                
        this.keyBinds.put(KeyEvent.VK_UP, UP);
        this.keyBinds.put(KeyEvent.VK_DOWN, DOWN);
        this.keyBinds.put(KeyEvent.VK_LEFT, LEFT);
        this.keyBinds.put(KeyEvent.VK_RIGHT, RIGHT);
    }
        
    public String translateInput(Object input) {
        return this.keyBinds.get((Integer)input); // Safe cast as this is a Keyboard based InputTranslator
    }
		
}
