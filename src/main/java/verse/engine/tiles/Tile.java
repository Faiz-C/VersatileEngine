package verse.engine.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import verse.engine.graphics.IDrawable;
import verse.engine.physics.CollidableObject;

/**
 * Represents a simple Tile placed that can be placed onto a Tile map. A tile is either solid, meaning
 * it can collide with other CollidableObjects, or not meaning it can be passed through (for example,
 * a background tile).
 * 
 * @author Faiz Gull Chaudhry
 */
public class Tile extends CollidableObject implements IDrawable{

    private BufferedImage tileImage;
    private boolean solid;
        
    public Tile(BufferedImage tileImage, boolean solid) {
        super(0, 0, tileImage.getWidth(), tileImage.getHeight());
        this.tileImage = tileImage;
        this.solid = solid;
    }
        
    /**
     * @return The image of the tile
     */
    public BufferedImage getImage() {
        return this.tileImage;
    }
        
    /**
     * @return A new tile which is a copy of this one
     */
    public Tile copy() {
        return new Tile(this.tileImage, this.solid);
    }
        
    @Override
    public boolean hasCollided(CollidableObject collidableObject) {
        return (this.solid) ? super.hasCollided(collidableObject) : false;
    }
        
    /**
     * Set whether or not the tile can be passed through/collided with
     * @param b -> True for solid, false for can pass through
     */
    public void setSolid(boolean b) {
        this.solid = b;
    }
        
    /**
     * Sets the image of the tile to the one given
     * @param tileImage -> The new image of the tile
     */
    public void setTileImage(BufferedImage tileImage) {
        this.tileImage = tileImage;
        this.setWidth(tileImage.getWidth());
        this.setHeight(tileImage.getHeight());
    }

    public void draw(Graphics2D g, int x, int y) {
        g.drawImage(this.tileImage, x, y, null);
    }
	
}
