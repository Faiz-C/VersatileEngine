package verse.engine.graphics;

import java.awt.Graphics2D;

/**
 * An interface encompassing the idea of an object within the game having the ability to be drawn to the
 * screen for the player to see. For example, the player sprite for a game.
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IDrawable {
        
    /**
     * Draws a representation of the Object onto the screen.
     * 
     * @param g -> The Graphics2D Object to use for drawing
     * @param x -> The top left x coordinate to start drawing onto the screen
     * @param y -> The top left y coordinate to start drawing onto the screen
     */
    public void draw(Graphics2D g, int x, int y);

}
