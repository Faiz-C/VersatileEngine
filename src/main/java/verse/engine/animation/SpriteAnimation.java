package verse.engine.animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpriteAnimation extends Animation{
	
	private BufferedImage[] frames;
	
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
