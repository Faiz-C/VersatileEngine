package verse.engine.manualTesting.collision;

import java.awt.Graphics2D;
import verse.engine.physics.CollidableObject;

public class TestObject extends CollidableObject{

	
	public TestObject(int width, int height, int xPos, int yPos) {
		super(width, height, xPos, yPos);
	}
	
	public void draw(Graphics2D g) {
		g.fillRect((int)this.position.getX(), (int)this.position.getY(), this.width, this.height);
	}

}
