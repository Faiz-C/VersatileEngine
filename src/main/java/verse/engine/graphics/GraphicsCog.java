package verse.engine.graphics;

import java.awt.AlphaComposite;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import verse.engine.IEngineCog;
import verse.engine.state.GameState;

/**
 * The Graphics Cog is responsible for drawing the graphics of the game. When run it expects to receive 
 * both a Container to draw on and an image to draw onto it. 
 * 
 * @author Faiz Chaudhry
 */
public class GraphicsCog implements IEngineCog{

    private BufferedImage renderedImage;
    private Container canvas;    
    
    public void initTurn(Object... args) {
        this.renderedImage = (BufferedImage) args[1];
        this.canvas = (Container) args[0];
        GameState state = (GameState) args[2];
        
        // Clear the screen for the next set of rendering
        Graphics2D imageGraphics = (Graphics2D) this.renderedImage.getGraphics();
        imageGraphics.setComposite(AlphaComposite.Clear);
        imageGraphics.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        imageGraphics.setComposite(AlphaComposite.SrcOver);

        // Draw the state to the image
        state.draw(imageGraphics, 0, 0);
    }
    
    public void turnCog() {
        // Draw the newly rendered image to the screen for the user
        Graphics screenGraphics = this.canvas.getGraphics();
        screenGraphics.drawImage(this.renderedImage, 0, 0, this.canvas.getWidth(), this.canvas.getHeight(), null);
        screenGraphics.dispose();
    }
    
}
