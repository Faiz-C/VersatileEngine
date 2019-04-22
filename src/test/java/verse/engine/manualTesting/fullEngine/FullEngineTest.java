package verse.engine.manualTesting.fullEngine;

import javax.swing.SwingUtilities;
import verse.engine.graphics.VFrame;
import verse.engine.VerseEngine;

public class FullEngineTest {

    public static void main(String args[]) {
      SwingUtilities.invokeLater(new Runnable() {
                        
                public void run() {
                    new VFrame("Testing", VFrame.STANDARD, new EngineContainer(60, VFrame.STANDARD));
                }
            });
            
    }
    
}
