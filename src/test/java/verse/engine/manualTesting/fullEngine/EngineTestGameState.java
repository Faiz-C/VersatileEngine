package verse.engine.manualTesting.fullEngine;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;

import verse.engine.animation.*;
import verse.engine.sound.*;
import verse.engine.utils.*;
import verse.engine.tiles.*;
import verse.engine.state.GameState;
import verse.engine.VerseEngine;

public class EngineTestGameState extends GameState {

    private Animation downMovement;
    private TileMapManager tileMapManager;
    
    public EngineTestGameState(Dimension dimension) {
        this.init(dimension);
    }

    private void init(Dimension dimension) {

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/test/resources/spritesTest.png"));
            SpriteSheetHandler handler = new SpriteSheetHandler();
            
            handler.setSpriteSheet(new SpriteSheet(spriteSheet, 28, 28));			
            this.downMovement = new SpriteAnimation(handler.cropRow(1), Animation.STANDARD_DELAY, Animation.INFINITE_LOOP);
			
            this.addUpdatableAspect(this.downMovement);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.tileMapManager = new TileMapManager("src/test/resources/tileSetTest", "src/test/resources/tileMapTest");
        TileMap tileMap = this.tileMapManager.getTileMap("Dungeon Level");
        tileMap.setScreenDimensions(dimension);
                
        // Just note that this test map is just one screen though, you can test it on your own!
        tileMap.setPivotPoint(0, 0); // Change pivot point of TileMap to see different parts of it 

        GameSound sound = new GameSound("src/test/resources/Thrill.mp3", true);
        VerseEngine.getInstance().getSoundManager().addSound(SoundManager.BGM, sound);
        VerseEngine.getInstance().getSoundManager().adjustVolume(SoundManager.BGM, 0.5f);
        VerseEngine.getInstance().getSoundManager().playSound(SoundManager.BGM);

    }

    public void draw(Graphics2D g, int x, int y) {
        this.tileMapManager.getTileMap("Dungeon Level").draw(g, 0, 0);
        this.downMovement.draw(g, 60, 400);
    }
}
