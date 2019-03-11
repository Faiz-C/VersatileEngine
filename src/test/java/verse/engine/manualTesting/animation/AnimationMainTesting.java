package verse.engine.manualTesting.animation;

import javax.swing.SwingUtilities;
import verse.engine.graphics.VFrame;

public class AnimationMainTesting {
        
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
                        
                public void run() {
                    new VFrame("Testing", VFrame.STANDARD, new AnimationTest(60, VFrame.STANDARD));
                }
            });
                
    }

}
