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

/**
 * The core engine itself. The VerseEngine holds all components needed to deal with the underlying game engine for 2D games. 
 * The VerseEngine is restricted to a singleton and should be run in its own thread to avoid conflicts with the main thread
 * launching the game. The VerseEngine deals with input, rendering, and updating the game through the PREDEFINED components
 * for each respectively. The engine is very straight foward and is extensible, allowing for others to add components of their
 * own (SOON TO COME).
 *
 * @author Faiz Chaudhry
 */
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

    // Components needed for sound
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

    /**
     * @return The SoundManager instance the engine is using to handle playing/pausing/stoping sounds within the game.
     */
    public SoundManager getSoundManager() {
        return this.soundManager;
    }
    
    /**
     * @return The GameStateManager instance the engine is using to handle the actual states of the game.
     */
    public GameStateManager getStateManager() {
        return this.stateManager;
    }

    /**
     * @return The ActionManager instance the engine is using to keep track of what actions are happening with the game.
     */
    public ActionManager getActionManager() {
        return this.actionManager;
    }

    /**
     * Sets the FPS of the game to the given fps.
     *
     * @param fps -> The desired fps for the engine to run the game at 
     */
    public void setFPS(long fps) {
        this.fps = fps;
    }
    
    /**
     * Adds the given InputDevice to the engine under the given name for the engine to listen for.
     *
     * @param name -> A String to refer to the given InputDevice by. 
     * @param inputDevice -> The InputDevice wanting to be added for the engine to listen for
     */ 
    public void addInputDevice(String name, InputDevice inputDevice) {
        this.inputCogs.put(name, new InputProcessingCog(inputDevice.getInputProcessor()));
    }

    
    /**
     * Removes the InputDevice associated with the given name from those the engine listens for.
     *
     * @param name -> The String associated with the InputDevice wanting to be removed. 
     */ 
    public void removeInputDevice(String name) {
        this.inputCogs.remove(name);
    }

    
    /**
     * Sets the container which the game is seen through (is displayed to). 
     *
     * @param display -> The actual Container instance wanting to be used to display the game.
     * @param dimension -> The Dimension of the given Container (width and height of the Container) 
     */ 
    public void setDisplayContainer(Container display, Dimension dimension) {
        this.display = display;
        this.displayImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    
    /**
     * @return The Container the engine is currently using to display the game. 
     */ 
    public Container getDisplayContainer() {
        return this.display;
    }

    /**
     * @return The current instance of the VerseEngine
     */
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

            // Wait our calculated time until the next game loop
            try {
                Thread.sleep(wait);
            }
            catch(Exception e) {
                ErrorHandler.raiseError("Error occured during game loop", e);
            }
            
        }
    }

}
