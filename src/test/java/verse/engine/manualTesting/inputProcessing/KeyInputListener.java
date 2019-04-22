package verse.engine.manualTesting.inputProcessing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import verse.engine.inputProcessing.ActionManager;
import verse.engine.inputProcessing.IInputTranslator;
import verse.engine.utils.ErrorHandler;

/**
 * The purpose of the KeyInputListener is to specifically listen for and translate keyboard based input.
 * 
 * @author Faiz Gull Chaudhry
 */
public class KeyInputListener extends KeyAdapter{

    private String lastActionTaken;
    private IInputTranslator inputTranslator;
    private ActionManager actionManager;
        
    public KeyInputListener(IInputTranslator inputTranslator, ActionManager actionManager) {
        this.inputTranslator = inputTranslator;
        this.actionManager = actionManager;
        this.lastActionTaken = null;
    }
        
    @Override
    public void keyPressed(KeyEvent e) {

        // Translate the input first
        String action = this.inputTranslator.translateInput(e.getKeyCode());
        if (action != null && action != this.lastActionTaken) {
                
            try {
                
                // Stop performing the last action if we have one
                if (this.lastActionTaken != null) this.actionManager.stopPerforming(this.lastActionTaken);
                        
                // Start performing the requested action
                this.actionManager.startPerforming(action);
                        
                // Update the last performed action
                this.lastActionTaken = action;
                        
            }catch (Exception ex) {
                ErrorHandler.raiseError("Failed to change/start action", ex);        		
            }
        }
    }
        
    @Override
    public void keyReleased(KeyEvent e) {
        // Translate the input first
        String action = this.inputTranslator.translateInput(e.getKeyCode());
        if (action != null) {
            try {
                // Stop using the given action
                this.actionManager.stopPerforming(action);
                this.lastActionTaken = null;
                        
            } catch(Exception ex) {
                ErrorHandler.raiseError("Failed to stop performing an action", ex);
            }

        }
    }
	
}
