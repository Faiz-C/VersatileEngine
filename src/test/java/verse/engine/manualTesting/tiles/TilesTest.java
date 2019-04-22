package verse.engine.manualTesting.tiles;

import verse.engine.graphics.VFrame;

public class TilesTest {
        
    @SuppressWarnings("unused")
    public static void main(String args[]) {	
        VFrame frame = new VFrame("Test", VFrame.STANDARD, new TileTestContainer(60, VFrame.STANDARD));			
    }

}
