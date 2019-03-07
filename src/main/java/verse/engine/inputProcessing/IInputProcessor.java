package verse.engine.inputProcessing;


/**
 * An InputProcessor is tasked with understanding WHAT to do with state changes between the GameActions.
 * It is responsible for taking the current state of the in game actions and making the game itself respond
 * to them.
 * 
 * For example, if an action such as "MOVE_UP" is in use then when the InputProcessor is called it may have
 * the game move the player up.
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IInputProcessor {

    /**
     * When called the new state of in game actions are processed and the game itself should be updated accordingly
     * to the changes.
     * 
     * @param actionManager -> An GameActionManager which has the most up to date states of the in game actions
     */
    public void processInput(ActionManager actionManager);
	
}
