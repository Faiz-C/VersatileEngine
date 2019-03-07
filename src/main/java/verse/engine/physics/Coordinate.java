package verse.engine.physics;

public class Coordinate {
        
    private double x, y;
        
    public Coordinate(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
        
    public void setX(double x) {
        this.x = x;
    }
        
    public void setY(double y) {
        this.y = y;
    }
        
    public double getX() {
        return this.x;
    }
        
    public double getY() {
        return this.y;
    }
        
    public double xDifference(Coordinate coordinate) {
        return (coordinate.getX() - this.x);
    }
        
    public double yDifference(Coordinate coordinate) {
        return (coordinate.getY() - this.y);
    }
        
    public double distanceBetween(Coordinate coordinate) {
        return Math.sqrt((this.xDifference(coordinate) * this.xDifference(coordinate)) + (this.yDifference(coordinate) * this.yDifference(coordinate)));
    }
        
    public void add(Coordinate coordinate) {
        this.x += coordinate.getX();
        this.y += coordinate.getY();
    }

}
