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
 * @author Faiz Gull Chaudhry
 */
public class UpdateCog implements IEngineCog{

    private Set<IUpdatable> entitesToUpdate;
        
    public UpdateCog() {
        this.entitesToUpdate = new HashSet<IUpdatable>();
    }
        
    public void initTurn(Object... args) {
        for (Object o : args) {
            this.entitesToUpdate.add((IUpdatable) o);
        }
    }

    public void turnCog() {
        for (IUpdatable updatable : this.entitesToUpdate) {
            updatable.update();
        }
    }

}
