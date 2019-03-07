package verse.engine.utils;

import java.awt.image.BufferedImage;

public class SpriteSheetHandler {
        
    private SpriteSheet spriteSheet;
    private int columnTotal, rowTotal;
        
    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        
		this.columnTotal = this.spriteSheet.getWidth() / this.spriteSheet.getSpriteWidth();
		this.rowTotal = this.spriteSheet.getHeight() / this.spriteSheet.getSpriteHeight();
    }
    
	/**
	 * @return The total rows of sprites on the SpriteSheet
	 */
	public int getRowTotal() {
		return this.rowTotal;
	}
	
	/**
	 * @return The total columns of sprites on the SpriteSheet
	 */
	public int getColumnTotal() {
		return this.columnTotal;
	}
        
	/**
	 * Crops a subimage of the sprite sheet which has size equal to w * h and starts from coordinates
	 * (x,y) on the image itself. 
	 * 
	 * @param x -> The top left x coordinate 
	 * @param y -> The top left y coordinate
	 * @param w -> The width of how much to crop
	 * @param h -> The height of how much to crop
	 * @return A subimage of the sprite sheet cropped using the given information
	 */
	public BufferedImage crop(int x, int y, int w, int h) {
		return this.spriteSheet.getImage().getSubimage(x, y, w, h);
	}	
	
	/**
	 * Crops out every sprite in the row given and returns them all as separate images in a BufferedImage array.
	 * Note, rowNum * spriteHeight MUST be within the boundaries of the image for a valid return.
	 * 
	 * @param rowNum -> The row number of the spriteSheet wanting to be cropped
	 * @return The ENTIRE row of sprites as a BufferedImage array for the row number given or null if the given number is invalid
	 */
	public BufferedImage[] cropRow(int rowNum) {
		BufferedImage[] sprites = new BufferedImage[this.columnTotal];
		
		if (this.spriteSheet != null) {
		
			int yCoordinate = rowNum * this.spriteSheet.getSpriteHeight();
			if (yCoordinate < 0 || yCoordinate > this.spriteSheet.getSpriteHeight() * this.rowTotal) return null;
			
			int xDistance = 0;
			for (int i = 0; i < this.columnTotal; i++) {
				sprites[i] = this.crop(xDistance, yCoordinate, this.spriteSheet.getSpriteWidth(), this.spriteSheet.getSpriteHeight());
				xDistance += this.spriteSheet.getSpriteWidth();
			}
		
		}
		
		return sprites;
	}
	
	/**
	 * Crops out every sprite in the column given and returns them all as separate images in a BufferedImage array.
	 * Note, columnNum * spriteHeight MUST be within the boundaries of the image for a valid return.
	 * 
	 * @param columnNum -> The column number of the spriteSheet wanting to be cropped
	 * @return The ENTIRE column of sprites as a BufferedImage array for the column number given or null if the given number is invalid
	 */
	public BufferedImage[] cropColumn(int columnNum) {
		BufferedImage[] sprites = new BufferedImage[this.rowTotal];
		
		if (this.spriteSheet != null) {
			
			int xCoordinate = columnNum * this.spriteSheet.getSpriteHeight();
			if (xCoordinate < 0 || xCoordinate > this.spriteSheet.getSpriteHeight() * this.rowTotal) return null;
			
			int yDistance = 0;
			for (int z = 0; z < this.rowTotal; z++) {
				sprites[z] = this.crop(xCoordinate, yDistance, this.spriteSheet.getSpriteWidth(), this.spriteSheet.getSpriteHeight());
				yDistance += this.spriteSheet.getSpriteWidth();
			}
		}

		
		return sprites;
	}

}
