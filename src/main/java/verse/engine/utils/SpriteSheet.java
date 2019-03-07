package verse.engine.utils;

import java.awt.image.BufferedImage;

/**
 * A model representation of a simple sprite sheet.
 *
 * @author Faiz Chaudhry
 */
public class SpriteSheet {
        
    private BufferedImage spriteSheet;
    private int width, height, spriteWidth, spriteHeight;
        
    public SpriteSheet(BufferedImage spriteSheet, int spriteWidth, int spriteHeight) {
        this.spriteSheet = spriteSheet;
        this.width = spriteSheet.getWidth();
        this.height = spriteSheet.getHeight();
        
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        
    }

    /**
     * @return The actual image which the sprite sheet is based on
     */
    public BufferedImage getImage() {
        return this.spriteSheet;
    }

    /**
     * @return The width of the sprite(s) found on the sprite sheet
     */
    public int getSpriteWidth() {
    	return this.spriteWidth;
    }
    
    /**
     * @return The height of the sprite(s) found on the sprite sheet
     */
    public int getSpriteHeight() {
    	return this.spriteHeight;
    }

    /**
     * @return The width of the sprite sheet image itself
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return The height of the sprite sheet image itself
     */   
    public int getHeight() {
        return this.height;
    }

}
