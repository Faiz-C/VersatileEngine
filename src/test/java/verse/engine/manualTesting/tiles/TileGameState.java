package verse.engine.manualTesting.tiles;

import java.awt.Dimension;
import java.awt.Graphics2D;

import verse.engine.tiles.TileMap;
import verse.engine.tiles.TileMapManager;
import verse.engine.state.GameState;

public class TileGameState extends GameState {

    private TileMapManager tileMapManager;
    private TileMap tileMap;
     
    public TileGameState(Dimension dimension) {

        this.tileMapManager = new TileMapManager("src/test/resources/tileSetTest.json", "src/test/resources/tileMapTest.json");
        this.tileMap = this.tileMapManager.getTileMap("Test Map");
        this.tileMap.setScreenDimensions(dimension);
                
        // Just note that this test map is just one screen though, you can test it on your own!
        this.tileMap.setPivotPoint(0, 0); // Change pivot point of TileMap to see different parts of it
         
    }

    public void draw(Graphics2D g, int x, int y) {
        this.tileMapManager.getTileMap("Test Map").draw(g, 0, 0);
    }
    
}
