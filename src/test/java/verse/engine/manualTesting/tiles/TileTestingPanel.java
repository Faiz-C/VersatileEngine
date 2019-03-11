package verse.engine.manualTesting.tiles;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import verse.engine.IEngineCog;
import verse.engine.graphics.GraphicsCog;
import verse.engine.utils.ErrorHandler;
import verse.engine.tiles.TileMap;
import verse.engine.tiles.TileMapManager;

@SuppressWarnings("serial")
public class TileTestingPanel extends JPanel implements Runnable{
	
	private long fps;
	private IEngineCog graphicsCog;
	private BufferedImage gameImage;
	private Graphics2D graphics;
	private Thread thread;
	private TileMapManager tileMapManager;
	private TileMap tileMap;
	
	public TileTestingPanel(long fps, Dimension dimension) {
		super();
		
		this.setPreferredSize(dimension);
		this.setFocusable(true);
		this.requestFocus();
		
		this.fps = 1000 / fps;
		this.graphicsCog = new GraphicsCog();
		this.init(dimension);
	}
	
	// Setup the Image we use to draw and the graphics we use to draw on it
	private void init(Dimension dimension) {
		this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) gameImage.getGraphics();
	
		this.tileMapManager = new TileMapManager("src/test/resources/tileSetTest", "src/test/resources/tileMapTest");
		this.tileMap = this.tileMapManager.getTileMap("Dungeon Level");
		this.tileMap.setScreenDimensions(dimension);
		
		// Just note that this test map is just one screen though, you can test it on your own!
		this.tileMap.setPivotPoint(0, 0); // Change pivot point of TileMap to see different parts of it
		
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.start();
		}
	}

	public void run() {
		long start, elapsed, wait;

		while(true) {

			start = System.nanoTime();
			
			this.graphicsCog.initTurn(this, this.gameImage); // Wipe the screen
        	this.draw(); // Draw the map onto the gameImage
			this.graphicsCog.turnCog(); // Draw the image onto the screen
			
			elapsed = System.nanoTime() - start;
			wait =  this.fps - elapsed / 1000000;

			// Wait a bit before looping again
			if (wait < 0) wait = 5; // We can't wait negative time, fix it to 5 if it happens

			try{ Thread.sleep(wait); }
			catch(Exception e) { 
				ErrorHandler.raiseError("Could not properly sleep during game loop", e);
			}

		}
	}

	public void draw() {
		this.tileMapManager.getTileMap("Dungeon Level").draw(this.graphics, 0, 0);
	}
		
}
