package verse.engine;

import java.util.Map;
import java.util.HashMap;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import verse.engine.inputProcessing.*;
import verse.engine.state.*;
import verse.engine.sound.*;
import verse.engine.graphics.*;

// Want three main accessible componenents. One for updating, graphics, and input
public class VerseEngine implements Runnable{ 
    
    // Static instance for singleton
    private static VerseEngine instance;

    // fps
    private long fps;
    
    // Components needed for input
    private Map<String, InputDevice> inputDevices;
    private IEngineCog inputProcessingCog;
    private ActionManager actionManager;
    
    // Components needed for updating
    private IEngineCog updatingCog;
    
    // Components needed for rendering
    private Container display;
    private BufferedImage displayImage;
    private IEngineCog renderingCog;

    // GameStateManager for both rendering and updating
    private GameStateManager stateManager;

    // Sound
    private SoundManager soundManager;

    private VerseEngine() {
        this.inputDevices = new HashMap<String, InputDevice>();
        this.stateManager = new GameStateManager();
        this.soundManager = new SoundManager();
        this.actionManager = new ActionManager();
    }
    
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    public ActionManager getActionManager() {
        return this.actionManager;
    }

    public void setFPS(long fps) {
        this.fps = fps;
    }

    public void addInputDevice(String name, InputDevice inputDevice) {
        this.inputDevices.put(name, inputDevice);
    }

    public void removeInputDevice(String name) {
        this.inputDevices.remove(name);
    }

    public void setDisplayContainer(Container display, Dimension dimension) {
        this.display = display;
        this.displayImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public Container getDisplayContainer() {
        return this.display;
    }

    public Graphics2D getDisplayGraphics() {
        return (Graphics2D) this.displayImage.getGraphics();
    }

    public static synchronized VerseEngine getInstance() {
        if (instance == null) {
            instance = new VerseEngine();
        }

        return instance;
    }

    public void run() {
        
    }

}
