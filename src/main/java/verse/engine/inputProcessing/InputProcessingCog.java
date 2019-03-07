package verse.engine.inputProcessing;

import verse.engine.IEngineCog;

/**
 * This is component of the Game Engine which processors user input as it comes in and then allows the
 * game to react to the input it processes.
 * 
 * @author Faiz Gull Chaudhry
 */
public class InputProcessingCog implements IEngineCog {

    private IInputProcessor inputProcessor;
    private ActionManager currentActionStates;
        
    public InputProcessingCog(IInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public void initTurn(Object... args) {
        this.currentActionStates = (ActionManager) args[0];
    }

    public void turnCog() {
        this.inputProcessor.processInput(this.currentActionStates);
    }
	
}
