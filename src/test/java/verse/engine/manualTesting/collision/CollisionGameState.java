package verse.engine.manualTesting.collision;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import verse.engine.state.GameState;

@SuppressWarnings("serial")
public class CollisionGameState extends GameState{        
        
    private TestObject obj1, obj2;
        
    public CollisionGameState() {
        super();
        this.init();
    }
        
    private void init() {
        this.obj1 = new TestObject(32, 32, 100, 100);
        this.obj2 = new TestObject(32, 32, 10, 10);
    }
        
    public void draw(Graphics2D g, int x, int y) {
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

