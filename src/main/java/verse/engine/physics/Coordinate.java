package verse.engine.physics;

/**
 * A very simple Class to represent a Coordinate on the cartesian plane.
 *
 * @author Faiz Chaudhry
 */
public class Coordinate {
        
    private double x, y;
        
    public Coordinate(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Sets the current x coordinate to the given x coordinate
     *
     * @param x -> The new x coordinate to change to
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the current y coordinate to the given y coordinate
     *
     * @param y -> The new y coordinate to change to
     */   
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return The current x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return The current y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param coordinate -> The Coordinate to calculate with
     * @return The difference between the x coordinates of the given Coordinate and this Coordinate
     */
    public double xDifference(Coordinate coordinate) {
        return (coordinate.getX() - this.x);
    }

    /**
     * @param coordinate -> The Coordinate to calculate with
     * @return The difference between the y coordinates of the given Coordinate and this Coordinate
     */   
    public double yDifference(Coordinate coordinate) {
        return (coordinate.getY() - this.y);
    }

    /**
     * @param coordinate -> The Coordinate to calculate with
     * @return The euclidean difference between the given Coordinate and this Coordinate
     */
    public double distanceBetween(Coordinate coordinate) {
        return Math.sqrt((this.xDifference(coordinate) * this.xDifference(coordinate)) +
                         (this.yDifference(coordinate) * this.yDifference(coordinate)));
    }

    /**
     * Adds the given Coordinate to this Coordinate
     *
     * @param coordinate -> The Coordinate wanting to be added
     */
    public void add(Coordinate coordinate) {
        this.x += coordinate.getX();
        this.y += coordinate.getY();
    }

}
