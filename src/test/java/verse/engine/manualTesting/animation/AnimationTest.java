package verse.engine.manualTesting.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import verse.engine.IEngineCog;
import verse.engine.animation.Animation;
import verse.engine.animation.SpriteAnimation;
import verse.engine.dialogue.DialogueEvent;
import verse.engine.dialogue.DialoguePiece;
import verse.engine.graphics.GraphicsCog;
import verse.engine.updatable.UpdateCog;
import verse.engine.utils.DialogueHandler;
import verse.engine.utils.ErrorHandler;
import verse.engine.utils.SpriteSheet;
import verse.engine.utils.SpriteSheetHandler;

@SuppressWarnings("serial")
public class AnimationTest extends JPanel implements Runnable{
        
    private long fps;
    private IEngineCog graphicsCog, updateCog;
    private BufferedImage gameImage;
    private Graphics2D graphics;
    private Thread thread;
    private DialogueEvent dialogueEvent;

    private Animation upMovement, downMovement, leftMovement, rightMovement;
    
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

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/test/resources/spritesTest.png"));
            SpriteSheetHandler handler = new SpriteSheetHandler();
            
            handler.setSpriteSheet(new SpriteSheet(spriteSheet, 28, 28));			
            this.upMovement = new SpriteAnimation(handler.cropRow(0), Animation.LONG_DELAY, Animation.INFINITE_LOOP);
            this.downMovement = new SpriteAnimation(handler.cropRow(1), Animation.STANDARD_DELAY, Animation.INFINITE_LOOP);
            this.leftMovement = new SpriteAnimation(handler.cropRow(2), Animation.DOUBLED_DELAY, Animation.INFINITE_LOOP);
            this.rightMovement = new SpriteAnimation(handler.cropRow(3), Animation.NO_DELAY, Animation.INFINITE_LOOP);
			

        } catch (Exception e) {
            e.printStackTrace();
        }

        //this.updateCog.initTurn(this.dialogueEvent, this.upMovement, this.downMovement, this.leftMovement, this.rightMovement);
        this.updateCog.initTurn(this.dialogueEvent, this.upMovement, this.downMovement, this.leftMovement, this.rightMovement);
    }
        
        
    private void drawToScreen() {
        this.graphics.setColor(Color.WHITE);
        this.dialogueEvent.drawEvent(this.graphics, 1, 30);

        this.upMovement.draw(this.graphics, 20, 400);
        this.downMovement.draw(this.graphics, 60, 400);
        this.leftMovement.draw(this.graphics, 100, 400);
        this.rightMovement.draw(this.graphics, 140, 400);

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