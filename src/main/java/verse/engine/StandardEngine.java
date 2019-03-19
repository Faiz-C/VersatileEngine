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
    private Graphics2D gameGraphics;
    private Container canvas;
    
    public StandardEngine(Container canvas, Dimension dimension, long fps) {
        super();
        this.canvas = canvas;
        this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.gameGraphics = (Graphics2D) this.gameImage.getGraphics();
        this.fps = 1000 / fps;
    }

    @Override
    public void run() {

    }
    
}
