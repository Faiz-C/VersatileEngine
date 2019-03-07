package verse.engine.physics;

/**
 * Represents a specific version of a MapObject which is capable of moving around the 
 * game map itself.
 * 
 * @author Faiz Gull Chaudhry
 */
public abstract class MovableMapObject extends CollidableObject{
        
    public MovableMapObject(int x, int y, int oWidth, int oHeight) {
        super(x, y, oWidth, oHeight);
    }
        
    /**
     * Simple method that helps with moving position of the MovableMapObject to the given one
     * 
     * @param newX -> New x coordinate
     * @param newY -> New y coordinate
     */
    private void move(double newX, double newY) {
        this.setPosition(newX, newY);
    }
        
    /**
     * Moves the MovableMapObject left by the given amount.
     * 
     * @param xDelta -> How much distance (pixel-wise) is to be moved
     */
    public void moveLeft(int xDelta) {
        this.move(this.getPosition().getX() - xDelta, this.getPosition().getY());
    }
        
    /**
     * Moves the MovableMapObject right by the given amount.
     * 
     * @param xDelta -> How much distance (pixel-wise) is to be moved
     */
    public void moveRight(int xDelta) {
        this.move(this.getPosition().getX() + xDelta, this.getPosition().getY());
    }
        
    /**
     * Moves the MovableMapObject up by the given amount.
     * 
     * @param yDelta -> How much distance (pixel-wise) is to be moved
     */
    public void moveUp(int yDelta) {
        this.move(this.getPosition().getX(), this.getPosition().getY() - yDelta);
    }
        
    /**
     * Moves the MovableMapObject down by the given amount.
     * 
     * @param yDelta -> How much distance (pixel-wise) is to be moved
     */
    public void moveDown(int yDelta) {
        this.move(this.getPosition().getX(), this.getPosition().getY() + yDelta);
    }
        
    /**
     * The Below are just incase we need to specifically move diagonally 
     */
        
    /**
     * Moves the MovableMapObject up and to the left by the given amount.
     * 
     * @param xDelta -> How much to move on the x axis (pixel-wise)
     * @param yDelta -> How much to move on the y axis (pixel-wise)
     */
    public void moveUpAndLeft(int xDelta, int yDelta) {
        this.move(this.getPosition().getX() - xDelta, this.getPosition().getY() - yDelta);
    }
        
    /**
     * Moves the MovableMapObject up and to the right by the given amount.
     * 
     * @param xDelta -> How much to move on the x axis (pixel-wise)
     * @param yDelta -> How much to move on the y axis (pixel-wise)
     */
    public void moveUpAndRight(int xDelta, int yDelta) {
        this.move(this.getPosition().getX() + xDelta, this.getPosition().getY() - yDelta);
    }
        
    /**
     * Moves the MovableMapObject down and to the left by the given amount.
     * 
     * @param xDelta -> How much to move on the x axis (pixel-wise)
     * @param yDelta -> How much to move on the y axis (pixel-wise)
     */
    public void moveDownAndLeft(int xDelta, int yDelta) {
        this.move(this.getPosition().getX() - xDelta, this.getPosition().getY() + yDelta);
    }
        
    /**
     * Moves the MovableMapObject down and to the right by the given amount.
     * 
     * @param xDelta -> How much to move on the x axis (pixel-wise)
     * @param yDelta -> How much to move on the y axis (pixel-wise)
     */
    public void moveDownAndRight(int xDelta, int yDelta) {
        this.move(this.getPosition().getX() + xDelta, this.getPosition().getY() + yDelta);
    }

}
