package verse.engine.physics;

import java.util.ArrayList;
import java.util.List;

import verse.engine.updatable.IUpdatable;

/**
 * 
 *
 * @author Faiz Chaudhry
 */
public class GravityEnvironment implements IUpdatable{
        
    private List<CollidableObject> cObjects;
    private Coordinate gravityCoefficient;
        
    public GravityEnvironment(Coordinate gravityCoefficient, List<CollidableObject> cObjects) {
        this.setCollisionObjects(cObjects);
        this.gravityCoefficient = gravityCoefficient;
    }
        
    public GravityEnvironment(Coordinate gravityCoefficient) {
        this(gravityCoefficient, new ArrayList<CollidableObject>());
    }
        
    public void setCollisionObjects(List<CollidableObject> cObjects) {
        this.cObjects = cObjects;
    }
        
    public void addCollisionObject(CollidableObject cObject) {
        if (!this.cObjects.contains(cObject)) {
            this.cObjects.add(cObject);
        }
    }
        
    public void removeCollisionObject(CollidableObject cObject) {
        if (this.cObjects.contains(cObject)) {
            this.removeCollisionObject(cObject);
        }
    }

    public void update() {
        for (CollidableObject cObject : this.cObjects) {
            cObject.getPosition().add(this.gravityCoefficient);
        }
    }	

}
