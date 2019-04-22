package verse.engine.manualTesting.fullEngine;

import java.awt.Dimension;
import javax.swing.JPanel;

import verse.engine.VerseEngine;
import verse.engine.state.*;
import verse.engine.inputProcessing.*;
import verse.engine.manualTesting.collision.*;
import verse.engine.manualTesting.inputProcessing.*;
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
        
        EngineTestGameState testState = new EngineTestGameState(dimension);

        InputDevice keyboardInput = new InputDevice(new KeyInputTranslator(),
            new CollisionMovementProcessor(testState.getTestObject2(), testState.getTestObject1()));

        engine.getActionManager().modifyGameAction(KeyInputTranslator.UP, false);
        engine.getActionManager().modifyGameAction(KeyInputTranslator.DOWN, false);
        engine.getActionManager().modifyGameAction(KeyInputTranslator.LEFT, false);
        engine.getActionManager().modifyGameAction(KeyInputTranslator.RIGHT, false);

        engine.addInputDevice("KeyBoard Input", keyboardInput);

        this.addKeyListener(new KeyInputListener(keyboardInput.getInputTranslator(), engine.getActionManager()));
        
        try {
            engine.getStateManager().addGameState("Test", testState);
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
