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
import verse.engine.updatable.*;
import verse.engine.utils.*;

// Want three main accessible componenents. One for updating, graphics, and input
public class VerseEngine implements Runnable, IObserver{ 
    
    // Static instance for singleton
    private static VerseEngine instance;

    // fps
    private long fps;
    
    // Components needed for input
    private Map<String, IEngineCog> inputCogs; // Could have more than one type of input
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
        this.inputCogs = new HashMap<String, IEngineCog>();
        this.stateManager = new GameStateManager();
        this.soundManager = new SoundManager();
        this.actionManager = new ActionManager();

        this.updatingCog = new UpdateCog();
        this.renderingCog = new GraphicsCog();

        this.stateManager.addObserver(this);
    }

    public void update(Object... args) {
        this.updatingCog.initTurn(this.stateManager.getCurrentState());
    }
    
    public SoundManager getSoundManager() {
        return this.soundManager;
    }

    public GameStateManager getStateManager() {
        return this.stateManager;
    }

    public ActionManager getActionManager() {
        return this.actionManager;
    }

    public void setFPS(long fps) {
        this.fps = fps;
    }

    public void addInputDevice(String name, InputDevice inputDevice) {
        this.inputCogs.put(name, new InputProcessingCog(inputDevice.getInputProcessor()));
    }

    public void removeInputDevice(String name) {
        this.inputCogs.remove(name);
    }

    public void setDisplayContainer(Container display, Dimension dimension) {
        this.display = display;
        this.displayImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public Container getDisplayContainer() {
        return this.display;
    }

    public static synchronized VerseEngine getInstance() {
        if (instance == null) {
            instance = new VerseEngine();
        }

        return instance;
    }

    public void run() {
        long start, elapsed, wait;

        while(true) {

            start = System.nanoTime();

            // Process Input
            for (Map.Entry<String, IEngineCog> entry : this.inputCogs.entrySet()) {
                IEngineCog cog = entry.getValue();
                cog.initTurn(this.actionManager);
                cog.turnCog();
            }

            // Update Game State
            this.updatingCog.turnCog();

            // Draw Game State
            this.renderingCog.initTurn(this.display, this.displayImage, this.stateManager.getCurrentState());
            this.renderingCog.turnCog();

            // Calculate time lapse and wait time
            elapsed = System.nanoTime() - start;
            wait = (1000 / this.fps) - elapsed / 1000000;

            if (wait < 0) wait = 10;

            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                ErrorHandler.raiseError("Error occured during game loop", e);
            }
            
        }
    }

}
