package verse.engine.manualTesting.fullEngine;

import java.awt.Dimension;
import javax.swing.JPanel;

import verse.engine.VerseEngine;
import verse.engine.state.*;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class EngineContainer extends JPanel {
        
    private Thread thread;

    public EngineContainer(long fps, Dimension dimension) {
        super();
        this.setPreferredSize(dimension);
        this.setFocusable(true);
        this.requestFocus();


        // Setup Engine
        VerseEngine engine = VerseEngine.getInstance();
        engine.setFPS(fps);
        engine.setDisplayContainer(this, dimension);
        
        try {
            engine.getStateManager().addGameState("Test", new EngineTestGameState(dimension));
            engine.getStateManager().swapState("Test");
        }
        catch (Exception e) {
            ErrorHandler.raiseError("Problem Swapping State", e);
        }
        
    }
    
    // Don't exactly need to do this to start the engine but its fine for testing
    @Override
    public void addNotify() {
        super.addNotify();
        if (this.thread == null) {
            this.thread = new Thread(VerseEngine.getInstance());
            this.thread.start();
        }
    }

}
