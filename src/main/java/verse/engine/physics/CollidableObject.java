package verse.engine.physics;

/**
 * An abstraction of a game object which is able to collide with other objects within the game. For use with the physics
 * component of the engine. Uses the four corner collision model.
 * 
 * @author Faiz Chaudhry
 */
public abstract class CollidableObject {

    protected int width, height;
    protected Coordinate topRight, topLeft, bottomRight, bottomLeft, position;
        
    public CollidableObject(int width, int height, int xPos, int yPos) {
        this.width = width;
        this.height = height;
        this.position = new Coordinate(xPos, yPos);
                
        this.setCorners();
    }

    /**
     * Sets up the four corners outlining a rectangle around the game object.
     */
    private void setCorners() {
        this.topLeft = new Coordinate(this.position.getX(), this.position.getY());
        this.topRight = new Coordinate(this.position.getX() + this.width, this.position.getY());
        this.bottomLeft = new Coordinate(this.position.getX(), this.position.getY() + this.height);
        this.bottomRight = new Coordinate(this.position.getX() + this.width, this.position.getY() + this.height);
    }

    /**
     * Sets the position of where the game object is on the map.
     */
    public void setPosition(double newX, double newY) {
        this.position.setX(newX);
        this.position.setY(newY);
        this.setCorners();
    }

    /**
     * Sets the width of the MapObject
     * 
     * @param width -> Wanted Width
     */
    public void setWidth(int width) {
        this.width = width;
        this.setCorners();
    }

    /**
     * Sets the height of the MapObject
     * 
     * @param height -> Wanted Height
     */ 
    public void setHeight(int height) {
        this.height = height;
        this.setCorners();
    }

    /**
     * @return The left border of the rectangle outlining the game object
     */
    public double getLeftBorder() {
        return this.topLeft.getX();
    }

    /**
     * @return The right border of the rectangle outlining the game object
     */
    public double getRightBorder() {
        return this.topRight.getX();
    }

    /**
     * @return The top border of the rectangle outlining the game object
     */
    public double getTopBorder() {
        return this.topLeft.getY();
    }

    /**
     * @return The bottom border of the rectangle outlining the game object
     */
    public double getBottomBorder() {
        return this.bottomLeft.getY();
    }

    /**
     * @return The current position of the game object
     */
    public Coordinate getPosition() {
        return this.position;
    }

    /**
     * @return The top right corner of the rectangle outlining the game object
     */
    public Coordinate getTopRightCoordinate() {
        return this.topRight;
    }

    /**
     * @return The top left corner of the rectangle outlining the game object
     */
    public Coordinate getTopLeftCoordinate() {
        return this.topLeft;
    }

    /**
     * @return The bottom right corner of the rectangle outlining the game object
     */
    public Coordinate getBottomRightCoordinate() {
        return this.bottomRight;
    }

    /**
     * @return The bottom left corner of the rectangle outlining the game object
     */
    public Coordinate getBottomLeftCoordinate() {
        return this.bottomLeft;
    }

    /**
     * @return The width of the rectangle outlining the game object
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return The height of the rectangle outlining the game object
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @param obj -> A CollidableObject to check with
     * @return True if this CollidableObject collides with the given CollidableObject, false otherwise
     */
    public boolean hasCollided(CollidableObject obj) {
        boolean withinLeftCollisionBounds = this.getRightBorder() >= obj.getLeftBorder() && this.getRightBorder() <= obj.getRightBorder(),
            withinRightCollisionBounds = this.getLeftBorder() <= obj.getRightBorder() && this.getLeftBorder() >= obj.getLeftBorder(),
            withinTopCollisionBounds = this.getTopBorder() >= obj.getTopBorder() && this.getTopBorder() <= obj.getBottomBorder(),
            withinBottomCollisionBounds = this.getBottomBorder() <= obj.getBottomBorder() && this.getBottomBorder() >= obj.getTopBorder();

        return (withinLeftCollisionBounds || withinRightCollisionBounds) && (withinTopCollisionBounds || withinBottomCollisionBounds); 
    }
	
}
