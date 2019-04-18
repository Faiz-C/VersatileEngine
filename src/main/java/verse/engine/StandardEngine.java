package verse.engine;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Container;
import java.util.Map;

import verse.engine.graphics.*;
import verse.engine.updatable.*;
import verse.engine.inputProcessing.*;
import verse.engine.utils.*;

/**
 * An implementation of an Engine which uses the predefined main cogs for Input, Updating and Graphics.
 *
 * @author Faiz Chaudhry
 */
public class StandardEngine extends Engine {

    // Game Loop
    private long fps;
    
    // Input Processing
    private IInputProcessor inputProcessor;
    private Map<String, IInputTranslator> inputTranslatorMap;
    private ActionManager actionManager;

    // Updating

    // Graphics
    private BufferedImage gameImage;
    private Container canvas;

    private final int MAIN_COG_INDEX = 0;
    private GameState currentState;
    
    public StandardEngine(Container canvas, Dimension dimension, long fps) {
        super();
        this.canvas = canvas;
        this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.fps = fps;
        this.currentState = null;
    }

    public void setGameState(GameState state) {
        this.currentState = state;
    }

    @Override
    public void run() {
        long startTime, elapsedTime, waitTime;

        long optimalWaitTime = 1000 / this.fps;

        // Get any important cogs...
        IEngineCog inputCog = this.getCog(MAIN_COG_INDEX, Engine.CogType.INPUT);
        IEngineCog graphicsCog = this.getCog(MAIN_COG_INDEX, Engine.CogType.GRAPHICS);

        if (this.currentState == null) {
            ErrorHandler.raiseError("No GameState loaded!", null);
            return;
        }
        
        while(true) {

            startTime = System.nanoTime(); // Get the current time

            // Init the Cogs (any which need consistent initalize before running)
            inputCog.initTurn(this.actionManager);
            graphicsCog.initTurn(this.canvas, this,gameImage, this.currentState);
            
            // Run the Cogs
            for (IEngineCog inputCog : this.getInputCogs()) {
                inputCog.run();
            }
            for (IEngineCog updateCog : this.getUpdateCogs()) {
                updateCog.run();
            }
            for (IEngineCog graphicsCog : this.getGraphicsCogs()) {
                graphicsCog.run();
            }

            
            // Calculate the elapsed time and actual wait time
            elapsedTime = System.nanoTime() - startTime;
            waitTime = optimalWaitTime - elapsedTime / 1000000;

            // A fail safe in case we get negative or zero wait time, just set it to a default optimal goal
            if (waitTime <= 0) waitTime = optimalWaitTime;

            // Now sleep the loop and wait according

            try {
                Thread.sleep(waitTime);
            } catch(Exception e) {
                ErrorHandler.raiseError("Problem attempting to sleep in game loop", e);
            }
            
        }

        
    }
    
}
