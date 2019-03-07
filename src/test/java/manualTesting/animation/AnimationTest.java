package manualTesting.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import verse.engine.IEngineCog;
import verse.engine.dialogue.DialogueEvent;
import verse.engine.dialogue.DialoguePiece;
import verse.engine.graphics.GraphicsCog;
import verse.engine.updatable.UpdateCog;
import verse.engine.utils.DialogueHandler;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class AnimationTest extends JPanel implements Runnable{
	
	private long fps;
	private IEngineCog graphicsCog, updateCog;
	private BufferedImage gameImage;
	private Graphics2D graphics;
	private Thread thread;
	private DialogueEvent dialogueEvent;
	
	public AnimationTest(long fps, Dimension dimension) {
		super();
		
		this.setPreferredSize(dimension);
		this.setFocusable(true);
		this.requestFocus();
		
		this.fps = 1000 / fps;
		this.graphicsCog = new GraphicsCog();
		this.updateCog = new UpdateCog();
		this.init(dimension);
	}
	
	// Setup the Image we use to draw and the graphics we use to draw on it
	private void init(Dimension dimension) {
		this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) gameImage.getGraphics();
	
		this.graphics.setFont(new Font("Century Gothic", Font.BOLD, 25));
		List<String> lines = DialogueHandler.splitTextForScreen("You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work.You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work."
				, this.graphics.getFontMetrics(), (int) dimension.getWidth());
		
		this.dialogueEvent = new DialogueEvent(new DialoguePiece("Faiz", lines));
	}
	

	
	private void drawToScreen() {
		this.graphics.setColor(Color.WHITE);
		this.dialogueEvent.drawEvent(this.graphics, 1, 15);
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
			
			this.updateCog.initTurn(this.dialogueEvent);
			this.graphicsCog.initTurn(this, this.gameImage);
			
			this.updateCog.turnCog();
			this.drawToScreen();
			this.graphicsCog.turnCog();

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
