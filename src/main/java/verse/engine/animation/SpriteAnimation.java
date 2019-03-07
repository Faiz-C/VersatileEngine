package verse.engine.animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * A specific implementation of an Animation. This one deals specifically with animating sprites based of a collection
 * of frames. Loops through the frames of the sprite at the rate of a given frame delay.
 *
 * @author Faiz Chaudhry
 */
public class SpriteAnimation extends Animation{
	
    private BufferedImage[] frames; // The frames to loop through 
	
    public SpriteAnimation(BufferedImage[] frames, int frameDelay, int loopCount) {
        super(frameDelay, loopCount);
        this.frames = frames;
        this.maxAnimationPosition = frames.length;
    }	
    
    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.drawImage(this.frames[this.currentAnimationPosition], x, y, null);
    }
    
}
