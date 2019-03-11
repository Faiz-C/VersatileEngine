package verse.engine.manualTesting.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import verse.engine.IEngineCog;
import verse.engine.graphics.GraphicsCog;
import verse.engine.graphics.VFrame;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class GraphicsTesting extends JPanel implements Runnable{
        
    private long fps;
    private IEngineCog graphicsCog;
    private BufferedImage gameImage;
    private Graphics2D graphics;
    private Thread thread;
        
    public GraphicsTesting(long fps, Dimension dimension) {
        super();
                
        this.setPreferredSize(dimension);
        this.setFocusable(true);
        this.requestFocus();
                
        this.fps = 1000 / fps;
        this.graphicsCog = new GraphicsCog();
        this.init();
    }
        
    // Setup the Image we use to draw and the graphics we use to draw on it
    private void init() {
        this.gameImage = new BufferedImage((int)VFrame.STANDARD.getWidth(), (int)VFrame.STANDARD.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.graphics = (Graphics2D) gameImage.getGraphics();
    }
        
        
    private void drawStuff() {
        DrawTesting.drawString(this.graphics);
        DrawTesting.drawRectangle(this.graphics);
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
                        
            this.graphicsCog.initTurn(this, this.gameImage);
                        
            this.drawStuff();
            this.graphicsCog.turnCog();
                        
            elapsed = System.nanoTime() - start;
            wait =  this.fps - elapsed / 1000000;

            // Wait a bit before looping again
            if (wait < 0) wait = 10; // We can't wait negative time, fix it to 5 if it happens

            try{ Thread.sleep(wait); }
            catch(Exception e) { 
                ErrorHandler.raiseError("Could not properly sleep during game loop", e);
            }

        }
    }
	
}
