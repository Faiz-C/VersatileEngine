package verse.engine.manualTesting.tiles;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import verse.engine.IEngineCog;
import verse.engine.graphics.GraphicsCog;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class TileTestingPanel extends JPanel implements Runnable{
        
    private long fps;
    private IEngineCog graphicsCog;
    private BufferedImage gameImage;
    private Thread thread;

    private TileGameState state;
       
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
        this.state = new TileGameState(dimension);
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
                        
            this.graphicsCog.initTurn(this, this.gameImage, this.state); // Wipe the image and draw onto it again
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
		
}
