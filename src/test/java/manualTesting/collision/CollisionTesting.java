package manualTesting.collision;

import javax.swing.SwingUtilities;

import verse.engine.graphics.VFrame;

public class CollisionTesting {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new VFrame("Testing", VFrame.STANDARD, new CollisionTestingPanel(60, VFrame.STANDARD));
			}
		});
		
	}

}
