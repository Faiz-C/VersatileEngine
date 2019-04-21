package verse.engine.manualTesting.graphics;

import java.awt.Graphics2D;
import verse.engine.state.GameState;

public class DrawingGameState extends GameState {
    
    public void draw(Graphics2D g, int x, int y) {
        DrawTesting.drawString(g);
        DrawTesting.drawRectangle(g);
    }
    
}
