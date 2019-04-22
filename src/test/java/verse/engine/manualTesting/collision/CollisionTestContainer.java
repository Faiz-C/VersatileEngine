package verse.engine.manualTesting.collision;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import verse.engine.manualTesting.inputProcessing.KeyInputListener;
import verse.engine.manualTesting.inputProcessing.KeyInputTranslator;
import verse.engine.IEngineCog;
import verse.engine.graphics.GraphicsCog;
import verse.engine.inputProcessing.*;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class CollisionTestContainer extends JPanel implements Runnable {        
        
    private long fps;
    private IEngineCog graphicsCog, inputCog;
    private BufferedImage gameImage;
    private Graphics2D graphics;
    private Thread thread;

    private CollisionGameState state;
    private ActionManager actionManager;
        
    public CollisionTestContainer(long fps, Dimension dimension) {
        super();
                
        this.setPreferredSize(dimension);
        this.setFocusable(true);
        this.requestFocus();
                
        this.fps = 1000 / fps;
        this.graphicsCog = new GraphicsCog();
        this.init(dimension);
                
        IInputProcessor inputProcessor = new CollisionMovementProcessor(this.state.getTestObject2(), this.state.getTestObject1());
        IInputTranslator inputTranslator = new KeyInputTranslator();
        this.actionManager = new ActionManager();
                
        this.actionManager.modifyGameAction(KeyInputTranslator.UP, false);
        this.actionManager.modifyGameAction(KeyInputTranslator.DOWN, false);
        this.actionManager.modifyGameAction(KeyInputTranslator.LEFT, false);
        this.actionManager.modifyGameAction(KeyInputTranslator.RIGHT, false);
                
        this.inputCog = new InputProcessingCog(inputProcessor);
                
        this.addKeyListener(new KeyInputListener(inputTranslator, this.actionManager));
    }
        
    // Setup the Image we use to draw and the graphics we use to draw on it
    private void init(Dimension dimension) {
        this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.graphics = (Graphics2D) gameImage.getGraphics();
                
        this.state = new CollisionGameState();       
    }
            
    @Override
    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void run() {
        long start, elapsed, wait;

        while(true) {

            start = System.nanoTime();
                        
            this.inputCog.initTurn(this.actionManager);
            this.graphicsCog.initTurn(this, this.gameImage, this.state);
                        
            this.inputCog.turnCog();
            this.graphicsCog.turnCog();
                        
            elapsed = System.nanoTime() - start;
            wait =  this.fps - elapsed / 1000000;

            // Wait a bit before looping again
            if (wait < 0) wait = 10; // We can't wait negative time, fix it to 5 if it happens

            try{ Thread.sleep(wait); }
            catch(Exception e) { 
                ErrorHandler.raiseError("Could not properly sleep during game loop", e);
            }

        }
    }
}
