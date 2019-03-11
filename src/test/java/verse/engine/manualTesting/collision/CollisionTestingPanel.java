package verse.engine.manualTesting.collision;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import manualTesting.inputProcessing.KeyInputListener;
import manualTesting.inputProcessing.KeyInputTranslator;
import verse.engine.IEngineCog;
import verse.engine.graphics.GraphicsCog;
import verse.engine.inputProcessing.*;
import verse.engine.utils.ErrorHandler;

@SuppressWarnings("serial")
public class CollisionTestingPanel extends JPanel implements Runnable{
	
	
	private long fps;
	private IEngineCog graphicsCog, inputCog;
	private BufferedImage gameImage;
	private Graphics2D graphics;
	private Thread thread;
	
	private TestObject obj1, obj2;
	private ActionManager actionManager;
	
	public CollisionTestingPanel(long fps, Dimension dimension) {
		super();
		
		this.setPreferredSize(dimension);
		this.setFocusable(true);
		this.requestFocus();
		
		this.fps = 1000 / fps;
		this.graphicsCog = new GraphicsCog();
		this.init(dimension);
		
		IInputProcessor inputProcessor = new CollisionMovementProcessor(this.obj2, this.obj1);
		IInputTranslator inputTranslator = new KeyInputTranslator();
		this.actionManager = new ActionManager();
		
		this.actionManager.modifyGameAction(KeyInputTranslator.UP, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.DOWN, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.LEFT, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.RIGHT, false);
		
		this.inputCog = new InputProcessingCog(inputProcessor);
		
		this.addKeyListener(new KeyInputListener(inputTranslator, this.actionManager));
	}
	
	// Setup the Image we use to draw and the graphics we use to draw on it
	private void init(Dimension dimension) {
		this.gameImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) gameImage.getGraphics();
		
		this.obj1 = new TestObject(32, 32, 100, 100);
		this.obj2 = new TestObject(32, 32, 10, 10);
		
	}
	
	// When drawing I need to find a way to clear before drawing onto a new image
	private void drawStuff() {
		this.graphics.setColor(Color.blue);
		this.obj1.draw(this.graphics);
		
		this.graphics.setColor(this.obj2.hasCollided(this.obj1) ? Color.red : Color.white);
		this.obj2.draw(this.graphics);
		
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
			
			this.inputCog.initTurn(this.actionManager);
			this.graphicsCog.initTurn(this, this.gameImage);
			
			this.inputCog.turnCog();
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
