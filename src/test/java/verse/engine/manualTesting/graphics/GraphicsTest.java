package verse.engine.manualTesting.graphics;

import javax.swing.SwingUtilities;

import verse.engine.graphics.VFrame;

public class GraphicsTest {
        
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
                        
                public void run() {
                    new VFrame("Testing", VFrame.STANDARD, new GraphicsTesting(60, VFrame.STANDARD));
                }
            });
                
    }

}
