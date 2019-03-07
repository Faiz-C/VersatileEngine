package verse.engine.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;

import verse.engine.utils.ErrorHandler;
import verse.engine.utils.SpriteSheet;
import verse.engine.utils.SpriteSheetHandler;

/**
 * A helper class to make building in game tilesets easier. A TileSet is a collection of Tiles
 * of the SAME dimensions and allows for the user to pick out of them and place them into a TileMap.
 * This utilizes an image and frame setup where the image is the all the tiles together and
 * the frame dictates which tiles (in order) are solid.
 * 
 * @author Faiz Gull Chaudhry
 */
public class TileSet {

    private int tileWidth, tileHeight;
    private List<Tile> tileSet;
        
    public TileSet(String imagePath, JSONArray frame, int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.loadTiles(imagePath, frame);
    }
        
    /**
     * Using the frame and the imagePath given this will load the tile set image and extract each
     * individual tile image, making Tiles out of them and storing each.
     * 
     * @param imagePath -> The path to the image of the tile set
     * @param frame -> An array which contains the solid values for each tile (in order!)
     */
    private void loadTiles(String imagePath, JSONArray frame) {
        try {
                        
            BufferedImage tileSetImage = ImageIO.read(new File(imagePath));
            SpriteSheetHandler handler = new SpriteSheetHandler();		
                        
            int rowCount = tileSetImage.getHeight() / this.tileHeight, colCount = tileSetImage.getWidth() / this.tileWidth;
                                                
            handler.setSpriteSheet(new SpriteSheet(tileSetImage, tileWidth, tileHeight));
                        
            this.tileSet = new ArrayList<Tile>();
                        
            for (int r = 0; r < rowCount; r++) {
                List<BufferedImage> row = Arrays.asList(handler.cropRow(r));
                                
                for (int c = 0; c < row.size(); c++) {
                    boolean solid = ((Long) frame.get(r * colCount + c)).intValue() == 1;
                    this.tileSet.add(new Tile(row.get(c), solid));
                }
                                
            }
                        
        } catch(Exception e) {
            ErrorHandler.raiseError("Could not load tileset from " + imagePath, e);
        }
    }
        
    /**
     * @param tileNum -> Which tile of the TileSet you want retrieved
     * @return A COPY of the wanted tile in the TileSet
     */
    public Tile getTile(int tileNum) {
        return this.tileSet.get(tileNum).copy();
    }
        
    /**
     * @return The number of Tiles in this TileSet
     */
    public int getSize() {
        return this.tileSet.size();
    }
	
    /**
     * @return The Width of the tiles within this TileSet
     */
    public int getTileWidth() {
        return this.tileWidth;
    }
	
    /**
     * @return The Height of the tiles within this TileSet
     */
    public int getTileHeight() {
        return this.tileHeight;
    }
	
}
