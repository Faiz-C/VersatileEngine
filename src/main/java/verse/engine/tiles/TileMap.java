package verse.engine.tiles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import verse.engine.graphics.IDrawable;

public class TileMap implements IDrawable{

    private Tile[][] map;
    private TileSet tileSet;
    private int mapWidth, mapHeight, screenWidth, screenHeight, pivotRow, pivotCol;
        
    public TileMap(TileSet tileSet, String mapPath, int mapWidth, int mapHeight) {
        this.tileSet = tileSet;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;	
        this.pivotRow = this.pivotCol = 0;
        this.loadMap(mapPath);
    }
        
    /**
     * Given a path to a file which holds map frame data (based around numbers relating to tiles from a tile set separated
     * by spaces) this method will build the Tiles out of the data.
     * 
     * @param mapPath -> A path to the map frame data wanting to be made into a TileMap
     */
    private void loadMap(String mapPath) {
        try {
             
            List<String> tileNumbers = Files.readAllLines(Paths.get(mapPath));
                        
            int colCount = this.mapWidth / this.tileSet.getTileWidth(), rowCount = this.mapHeight / this.tileSet.getTileHeight();
                        
            this.map = new Tile[rowCount][colCount];
                        
            for (int r = 0; r < rowCount; r++) {
                String[] tileNums = tileNumbers.get(r).split(" ");
                                
                for (int c = 0; c < colCount; c++) {
                    this.map[r][c] = this.tileSet.getTile(Integer.parseInt(tileNums[c]));
                    this.map[r][c].setPosition(r, c);
                }
            }
                        
        } catch(Exception e) {
                        
        }
    }

    /**
     * Set which row and column the TileMap should start drawing from.
     * 
     * @param newRow -> The row to start at
     * @param newCol -> The col to start at
     */
    public void setPivotPoint(int newRow, int newCol) {
        this.pivotRow = newRow;
        this.pivotCol = newCol;
    }
        
    /**
     * Set how large the visible screen is so that it the TileMap can be drawn
     * to fit it.
     * 
     * @param dimension -> The dimensions of the visible screen
     */
    public void setScreenDimensions(Dimension dimension) {
        this.screenWidth = (int)dimension.getWidth();
        this.screenHeight = (int)dimension.getHeight();
    }

    public void draw(Graphics2D g, int x, int y) {

        // Care to change this as right now to make sure to account for tileSizes that don't fit nicely with screens!
        int visibleRowCount = this.screenHeight / this.tileSet.getTileHeight(), visibleColCount = this.screenWidth / this.tileSet.getTileWidth();
                
        int maxRowCount = (visibleRowCount + this.pivotRow <= this.map.length) ? visibleRowCount + this.pivotRow : this.map.length,
            maxColCount = (visibleColCount + this.pivotCol <= this.map[0].length) ? visibleColCount + this.pivotCol : this.map[0].length;
                
        for (int r = this.pivotRow; r < maxRowCount; r++) {
            for (int c = this.pivotCol; c < maxColCount; c++) {
                Tile tile = this.map[r][c];
                tile.draw(g, x + (c - this.pivotCol) * this.tileSet.getTileWidth(), y + (r - this.pivotRow) * this.tileSet.getTileHeight());
            }
        }
    }
        
}
