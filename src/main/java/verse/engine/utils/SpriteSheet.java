package verse.engine.utils;

import java.awt.image.BufferedImage;

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
        
    public BufferedImage getImage() {
        return this.spriteSheet;
    }
        
    public int getSpriteWidth() {
    	return this.spriteWidth;
    }
    
    public int getSpriteHeight() {
    	return this.spriteHeight;
    }
    
    public int getWidth() {
        return this.width;
    }
        
    public int getHeight() {
        return this.height;
    }

}
