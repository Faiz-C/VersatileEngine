package manualTesting.graphics;

import java.awt.Graphics2D;

public class DrawTesting {
	
	
	
	public static void drawRectangle(Graphics2D g) {
		g.fillRect(125, 100, 45, 45);
	}
	
	public static void drawString(Graphics2D g) {
		g.drawString("This is a test", 125, 175);
	}

}
