package verse.engine.gameStates;

import java.util.List;
import java.util.ArrayList;

import verse.engine.graphics.IDrawable;
import verse.engine.updatable.IUpdatable;

/**
 * An state within the game, whether a menu or a level or a character creation screen all main components of a game are represented
 * by a GameState. A GameState encompasses all aspects for a state to be drawn and properly updated along with the engine.
 * 
 * @author Faiz Chaudhry
 */
public abstract class GameState implements IDrawable, IUpdatable {

    private List<IUpdatable> aspectsToUpdate;
    
    public GameState() {
        this.aspectsToUpdate = new ArrayList<IUpdatable>();
    }

    /**
     * Adds the given IUpdatable object into the list of objects which update for the GameState on the game loop.
     *
     * @param updatable -> IUpdatable object wanting to be added
     */
    protected void addUpdatableAspect(IUpdatable updatable) {
        this.aspectsToUpdate.add(updatable);
    }

    /**
     * Removes the given IUpdatable object into the list of objects which update for the GameState on the game loop.
     *
     * @param updatable -> IUpdatable object wanting to be removed
     */
    protected void removeUpdatableAspect(IUpdatable updatable) {
        this.aspectsToUpdate.remove(updatable);
    }
    
    public void update() {
        for (IUpdatable updatable : this.aspectsToUpdate) {
            updatable.update();
        }
    }
    
}
