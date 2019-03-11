package verse.engine.animation;

import java.awt.Graphics2D;

/**
 * A specific implementation of an Animation which is focused on animating text (a String). This implementation animates
 * the string onto the screen one character at a time.
 *
 * @author Faiz Chaudhry
 */
public class TextAnimation extends Animation{

    private String fullMessage; // The message to animate
	
    public TextAnimation(String fullMessage, int frameDelay, int loopCount) {
        super(frameDelay, loopCount);
        this.fullMessage = fullMessage;
        this.maxAnimationPosition = this.fullMessage.length();
    }
    
    @Override
    public void draw(Graphics2D g, int x, int y) {
        g.drawString(this.fullMessage.substring(0, this.currentAnimationPosition + 1), x, y);
    }
    
}
