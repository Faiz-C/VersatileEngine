package verse.engine.animation;

import java.awt.Graphics2D;

public class TextAnimation extends Animation{

	private String fullMessage;
	
	public TextAnimation(String fullMessage, int frameDelay, int loopCount) {
		super(frameDelay, loopCount);
		this.fullMessage = fullMessage;
		this.maxAnimationPosition = this.fullMessage.length();
	}

	@Override
	public void draw(Graphics2D g, int x, int y) {
		g.drawString(this.fullMessage.substring(0, this.currentAnimationPosition), x, y);
	}

}
