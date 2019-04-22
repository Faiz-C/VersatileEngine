package verse.engine.manualTesting.fullEngine;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;

import verse.engine.manualTesting.collision.*;
import verse.engine.animation.*;
import verse.engine.sound.*;
import verse.engine.utils.*;
import verse.engine.tiles.*;
import verse.engine.state.GameState;
import verse.engine.VerseEngine;

public class EngineTestGameState extends GameState {

    private Animation rightMovement;
    private TileMapManager tileMapManager;
    private TestObject obj1, obj2;
    
    public EngineTestGameState(Dimension dimension) {
        super();
        this.obj1 = new TestObject(32, 32, 100, 100);
        this.obj2 = new TestObject(32, 32, 10, 10);
        this.init(dimension);
    }

    private void init(Dimension dimension) {

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("src/test/resources/32x32-bat-sprite.png"));
            SpriteSheetHandler handler = new SpriteSheetHandler();
            
            handler.setSpriteSheet(new SpriteSheet(spriteSheet, 32, 32));			
            this.rightMovement = new SpriteAnimation(handler.cropRow(1), Animation.STANDARD_DELAY, Animation.INFINITE_LOOP);
			
            this.addUpdatableAspect(this.rightMovement);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.tileMapManager = new TileMapManager("src/test/resources/tileSetTest.json", "src/test/resources/tileMapTest.json");
        TileMap tileMap = this.tileMapManager.getTileMap("Test Map");
        tileMap.setScreenDimensions(dimension);
                
        // Just note that this test map is just one screen though, you can test it on your own!
        tileMap.setPivotPoint(0, 0); // Change pivot point of TileMap to see different parts of it 

        GameSound sound = new GameSound("src/test/resources/sounds/yummie_test_song.mp3", true);
        VerseEngine.getInstance().getSoundManager().addSound(SoundManager.BGM, sound);
        VerseEngine.getInstance().getSoundManager().adjustVolume(SoundManager.BGM, 0.5f);
        VerseEngine.getInstance().getSoundManager().playSound(SoundManager.BGM);
        
    }

    public void draw(Graphics2D g, int x, int y) {
        this.tileMapManager.getTileMap("Test Map").draw(g, 0, 0);
        this.rightMovement.draw(g, 60, 400);

        g.setColor(Color.blue);
        this.obj1.draw(g);
                
        g.setColor(Color.white);
        this.obj2.draw(g);
         
    }

    public TestObject getTestObject1() {
        return this.obj1;
    }

    public TestObject getTestObject2() {
        return this.obj2;
    }

}
