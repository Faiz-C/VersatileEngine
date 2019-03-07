package verse.engine.inputProcessing;

import java.util.HashMap;
import java.util.Map;

import verse.engine.exceptions.ActionNotTrackedException;

/**
 * The purpose of the ActionManager is to track the states of all the possible or a subset of all possible actions a 
 * user can take within the game. A game action can be either "happening" (in use/true) or "not happening" 
 * (not in use/false). 
 * 
 * @author Faiz Chaudhry
 */
public class ActionManager {
        
    private Map<String, Boolean> actionStatuses; // A Map using a One-to-One mapping system
        
    public ActionManager(Map<String, Boolean> actionStatuses) {
        this.setActionStatuses(actionStatuses);
    }

    public ActionManager() {
        this(new HashMap<String, Boolean>());
    }
        
    /**
     * Sets the current game action states to the given.
     * 
     * @param actionStatuses -> A Map which relates game actions to their current states in game
     */
    public void setActionStatuses(Map<String, Boolean> actionStatuses) {
        this.actionStatuses = actionStatuses;
    }
        
    /**
     * If given an action name which is already being tracked then its state will
     * be changed to the given. If it is not being tracked then it will start
     * being tracked and its starting state will be the given one.
     * 
     * @param action -> The desired game action's name wanting to be modified or added
     */
    public void modifyGameAction(String action, boolean state) {
        this.actionStatuses.put(action, state);
    }
        
    /**
     * Stops tracking the state of the given game action.
     * 
     * @param action -> The name of the desired game action to remove
     * @throws ActionNotTrackedException
     */
    public void removeGameAction(String action) throws ActionNotTrackedException{
        if (!this.actionStatuses.containsKey(action)) {
            throw new ActionNotTrackedException(action);
        }
        this.actionStatuses.remove(action);
            
    }
        
    /**
     * Returns whether the given game action is being performed or not.
     * 
     * @param action -> The desired game action's name to check
     * @return True if the given action is being performed, false if not
     * @throws ActionNotTrackedException 
     */
    public boolean isPerforming(String action) throws ActionNotTrackedException {
        if (!this.actionStatuses.containsKey(action)) {
            throw new ActionNotTrackedException(action);
        }

        return this.actionStatuses.get(action);
    }
        
    /**
     * An alternative way of modifying where this will specifically start performing
     * (set using) the given game action as long as the ActionManager is already
     * tracking it.
     * 
     * @param action -> The name of the game action which this manager is currently tracking
     * @throws ActionNotTrackedException 
     */
    public void startPerforming(String action) throws ActionNotTrackedException {
        if (!this.actionStatuses.containsKey(action)) {
            throw new ActionNotTrackedException(action);
        }	

        this.modifyGameAction(action, true);
    }
        
    /**
     * An alternative way of modifying where this will specifically stop performing
     * (set to not using) the given game action as long as the ActionManager is
     * already tracking it.
     * 
     * @param action -> The name of the game action which this manager is currently tracking
     * @throws ActionNotTrackedException 
     */
    public void stopPerforming(String action) throws ActionNotTrackedException {
        if (!this.actionStatuses.containsKey(action)) {
            throw new ActionNotTrackedException(action);
        }	

        this.modifyGameAction(action, false);
    }
	
}
