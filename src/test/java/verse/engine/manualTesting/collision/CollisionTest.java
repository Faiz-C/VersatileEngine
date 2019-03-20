package verse.engine.manualTesting.collision;

import javax.swing.SwingUtilities;

import verse.engine.graphics.VFrame;

public class CollisionTest {
        
    public static void main(String args[]) {
                
        SwingUtilities.invokeLater(new Runnable() {
                        
                public void run() {
                    new VFrame("Testing", VFrame.STANDARD, new CollisionIntegrationTest(60, VFrame.STANDARD));
                }
            });
		
    }

}
