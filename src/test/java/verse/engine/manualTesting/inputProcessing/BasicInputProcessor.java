package verse.engine.manualTesting.inputProcessing;

import verse.engine.exceptions.ActionNotTrackedException;
import verse.engine.inputProcessing.ActionManager;
import verse.engine.inputProcessing.IInputProcessor;
import verse.engine.utils.ErrorHandler;

public class BasicInputProcessor implements IInputProcessor{
        
    private final String[] BASIC_MOVEMENTS = {KeyInputTranslator.UP, KeyInputTranslator.DOWN, 
                                              KeyInputTranslator.LEFT, KeyInputTranslator.RIGHT};
        
    public void processInput(ActionManager actionManager) {
        System.out.println("---- STATUSES START ----");
        for (String s : BASIC_MOVEMENTS) {
            try {
                System.out.println(String.format("Action: %s, State: %s", s, actionManager.isPerforming(s)));
            } catch (ActionNotTrackedException e) {
                ErrorHandler.raiseError("Failed to display Action State Information", e);
            }
        }
        System.out.println("---- STATUSES END ----\n");
    }

	
	
}
