package verse.engine.manualTesting.animation;

import javax.swing.SwingUtilities;
import verse.engine.graphics.VFrame;

public class AnimationTest {
        
    public static void main(String args[]) {
         SwingUtilities.invokeLater(new Runnable() {
                        
                public void run() {
                    new VFrame("Testing", VFrame.STANDARD, new AnimationContainer(60, VFrame.STANDARD));
                }
            });
               
    }

}
