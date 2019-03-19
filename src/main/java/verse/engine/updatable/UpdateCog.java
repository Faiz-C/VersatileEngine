package verse.engine.updatable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import verse.engine.IEngineCog;

/**
 * The purpose of this EngineCog is to handle any and all parts of the
 * game which need to be updated during the game. More specifically it
 * performs its part inside the game loop itself.
 * 
 * @author Faiz Chaudhry
 */
public class UpdateCog implements IEngineCog{

    private IUpdatable state;
    
    public UpdateCog() {
        this.state = null;
    }
        
    public void initTurn(Object... args) {
        this.state = (IUpdatable) args[0];
    }

    public void turnCog() {
        this.state.update();
    }

}
