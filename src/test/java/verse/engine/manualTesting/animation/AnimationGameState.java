package verse.engine.manualTesting.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Font;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import verse.engine.animation.Animation;
import verse.engine.animation.SpriteAnimation;
import verse.engine.dialogue.DialogueEvent;
import verse.engine.dialogue.DialoguePiece;
import verse.engine.utils.*;
import verse.engine.gameStates.GameState;

public class AnimationGameState extends GameState {

    private DialogueEvent dialogueEvent;
    private Animation upMovement, downMovement, leftMovement, rightMovement;
    private Font font;
    
    public AnimationGameState(Dimension dimension, Graphics2D g) {
        this.init(dimension, g);
    }

    private void init(Dimension dimension, Graphics2D g) {
        this.font = new Font("Century Gothic", Font.BOLD, 25);
        g.setFont(this.font);
        List<String> lines = DialogueHandler.splitTextForScreen("You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work.You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work. You can put any text here and it will work as you think it should work."
                                                                , g.getFontMetrics(), (int) dimension.getWidth());
        
        this.dialogueEvent = new DialogueEvent(new DialoguePiece("Faiz", lines));

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/test/resources/spritesTest.png"));
            SpriteSheetHandler handler = new SpriteSheetHandler();
            
            handler.setSpriteSheet(new SpriteSheet(spriteSheet, 28, 28));			
            this.upMovement = new SpriteAnimation(handler.cropRow(0), Animation.LONG_DELAY, Animation.INFINITE_LOOP);
            this.downMovement = new SpriteAnimation(handler.cropRow(1), Animation.STANDARD_DELAY, Animation.INFINITE_LOOP);
            this.leftMovement = new SpriteAnimation(handler.cropRow(2), Animation.DOUBLED_DELAY, Animation.INFINITE_LOOP);
            this.rightMovement = new SpriteAnimation(handler.cropRow(3), Animation.NO_DELAY, Animation.INFINITE_LOOP);
			
            this.addUpdatableAspect(this.dialogueEvent);
            this.addUpdatableAspect(this.upMovement);
            this.addUpdatableAspect(this.downMovement);
            this.addUpdatableAspect(this.rightMovement);
            this.addUpdatableAspect(this.leftMovement);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g, int x, int y) {

        g.setColor(Color.WHITE);
        g.setFont(this.font);
        this.dialogueEvent.draw(g, 1, 30);

        this.upMovement.draw(g, 20, 400);
        this.downMovement.draw(g, 60, 400);
        this.leftMovement.draw(g, 100, 400);
        this.rightMovement.draw(g, 140, 400);

    }

}
