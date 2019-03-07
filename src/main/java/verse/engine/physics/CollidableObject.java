package verse.engine.physics;

public abstract class CollidableObject {

    protected int width, height;
    protected Coordinate topRight, topLeft, bottomRight, bottomLeft, position;
        
    public CollidableObject(int width, int height, int xPos, int yPos) {
        this.width = width;
        this.height = height;
        this.position = new Coordinate(xPos, yPos);
                
        this.setCorners();
    }
        
    private void setCorners() {
        this.topLeft = new Coordinate(this.position.getX(), this.position.getY());
        this.topRight = new Coordinate(this.position.getX() + this.width, this.position.getY());
        this.bottomLeft = new Coordinate(this.position.getX(), this.position.getY() + this.height);
        this.bottomRight = new Coordinate(this.position.getX() + this.width, this.position.getY() + this.height);
    }
        
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
        
    public double getLeftBorder() {
        return this.topLeft.getX();
    }
        
    public double getRightBorder() {
        return this.topRight.getX();
    }
        
    public double getTopBorder() {
        return this.topLeft.getY();
    }
        
    public double getBottomBorder() {
        return this.bottomLeft.getY();
    }
        
    public Coordinate getPosition() {
        return this.position;
    }
        
    public Coordinate getTopRightCoordinate() {
        return this.topRight;
    }
        
    public Coordinate getTopLeftCoordinate() {
        return this.topLeft;
    }
        
    public Coordinate getBottomRightCoordinate() {
        return this.bottomRight;
    }
        
    public Coordinate getBottomLeftCoordinate() {
        return this.bottomLeft;
    }
        
    public int getWidth() {
        return this.width;
    }
        
    public int getHeight() {
        return this.height;
    }
        
    public boolean hasCollided(CollidableObject obj) {
        boolean withinLeftCollisionBounds = this.getRightBorder() >= obj.getLeftBorder() && this.getRightBorder() <= obj.getRightBorder(),
            withinRightCollisionBounds = this.getLeftBorder() <= obj.getRightBorder() && this.getLeftBorder() >= obj.getLeftBorder(),
            withinTopCollisionBounds = this.getTopBorder() >= obj.getTopBorder() && this.getTopBorder() <= obj.getBottomBorder(),
            withinBottomCollisionBounds = this.getBottomBorder() <= obj.getBottomBorder() && this.getBottomBorder() >= obj.getTopBorder();

        return (withinLeftCollisionBounds || withinRightCollisionBounds) && (withinTopCollisionBounds || withinBottomCollisionBounds); 
    }
	
}
