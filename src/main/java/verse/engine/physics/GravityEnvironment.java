package verse.engine.physics;

import java.util.ArrayList;
import java.util.List;

import verse.engine.updatable.IUpdatable;

/**
 * A simple implementation of an environment which allows for gravity. If a game object is found in a gravity environment then
 * on every update cycle the gravity of the environment will be applied to the game object.
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

    /**
     * Sets the CollidableObjects of the GravityEnvironment to the given list of CollidableObjects 
     *
     * @param cObjects -> A list of CollidableObjects to place on the GravityEnvironment
     */
    public void setCollisionObjects(List<CollidableObject> cObjects) {
        this.cObjects = cObjects;
    }

    /**
     * Adds the given CollidableObject to the list of CollidableObjects of the GravityEnvironment
     * if it isn't already present. 
     *
     * @param cObject -> The wanted CollidableObject to add
     */
    public void addCollisionObject(CollidableObject cObject) {
        if (!this.cObjects.contains(cObject)) {
            this.cObjects.add(cObject);
        }
    }
    
    /**
     * Removess the given CollidableObject from the list of CollidableObjects of the GravityEnvironment
     * if it is present. 
     *
     * @param cObject -> The wanted CollidableObject to add
     */   
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
