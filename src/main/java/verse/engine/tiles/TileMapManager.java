package verse.engine.tiles;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import verse.engine.utils.ErrorHandler;

public class TileMapManager {

    private Map<String, TileSet> tileSets;
    private Map<String, TileMap> gameMaps;
        
    public TileMapManager(String tileSetJSONPath, String tileMapJSONPath) {
        this.tileSets = new HashMap<String, TileSet>();
        this.gameMaps = new HashMap<String, TileMap>();
        this.loadTiles(tileSetJSONPath);
        this.loadTileMaps(tileMapJSONPath);
    }
        
    /**
     * Using the given path, this method will fetch and load ALL tile sets within the given file.
     * @param jsonPath -> The path to the json object file containing information on all the tile sets for the game
     */
    private void loadTiles(String jsonPath) {
        try {
            File file = new File(jsonPath);
                        
            // Check if file exists and is readable
                        
            if (!file.exists() || !file.canRead()) {
                ErrorHandler.raiseError("File either doesn't exist or cannot be read from", null);
                return;
            }
                        
            // Read all the JSON strings from the given path
            List<String> tileSetInfo = Files.readAllLines(Paths.get(jsonPath));
                        
            for (String info : tileSetInfo) {
                Object obj = new JSONParser().parse(info);
                                
                JSONObject jObject = (JSONObject) obj;
                                
                // Fetch the info for the tile set
                String tileSetName = (String) jObject.get("TileSet"), imagePath = (String) jObject.get("ImagePath");
                int tileWidth = ((Long)jObject.get("TileWidth")).intValue(), tileHeight = ((Long) jObject.get("TileHeight")).intValue();
                JSONArray tileFrameArray = (JSONArray) jObject.get("Frame");
                                
                this.tileSets.put(tileSetName, new TileSet(imagePath, tileFrameArray, tileWidth, tileHeight));
                                                        
            }
                
        } catch(Exception e) {
            ErrorHandler.raiseError("Could not read JSON from file", e);
        }
    }
        
    /**
     * Using the given path, this method will fetch and load ALL TileMaps within the given file.
     * @param jsonPath -> The path to the json object file containing information on all the tile sets for the game
     */
    private void loadTileMaps(String jsonPath) {
        try {
            File file = new File(jsonPath);
                        
            // Check if file exists and is readable
                        
            if (!file.exists() || !file.canRead()) {
                ErrorHandler.raiseError("File either doesn't exist or cannot be read from", null);
                return;
            }
                        
            // Read all the JSON strings from the given path
            List<String> tileSetInfo = Files.readAllLines(Paths.get(jsonPath));
                        
            for (String info : tileSetInfo) {
                Object obj = new JSONParser().parse(info);
                                
                JSONObject jObject = (JSONObject) obj;
                                
                // Fetch information for each map
                String mapName = (String) jObject.get("MapName"), mapPath = (String) jObject.get("MapPath"), tileSetName = (String) jObject.get("TileSet");
                int mapWidth = ((Long) jObject.get("MapWidth")).intValue(), mapHeight = ((Long) jObject.get("MapHeight")).intValue();
                           
                this.gameMaps.put(mapName, new TileMap(this.getTileSet(tileSetName), mapPath, mapWidth, mapHeight));
                                                        
            }
                
        } catch(Exception e) {
            ErrorHandler.raiseError("Could not read JSON from file", e);
        }
    }
        
    /**
     * @param tileMapName -> Name of the TileMap wanted
     * @return The TileMap that associates with the given name, null if none exist with the given name
     */
    public TileMap getTileMap(String tileMapName) {
        return this.gameMaps.containsKey(tileMapName) ? this.gameMaps.get(tileMapName) : null;
    }
	
    /**
     * @param tileSetName -> The name of the wanted tile set
     * @return The tile set associated with the name given, null if none exist with the given name
     */
    public TileSet getTileSet(String tileSetName) {
        return this.tileSets.containsKey(tileSetName) ? this.tileSets.get(tileSetName) : null;
    }
	
}
