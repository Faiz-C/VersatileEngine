package verse.engine.graphics;

import java.awt.Dimension;
import java.awt.Container;

/**
 * A very simple container used for drawing onto. It doesn't do much else but can be added
 * onto.
 * 
 * @author Faiz Chaudhry
 */
public class DrawScreen extends Container{
        
    public DrawScreen(Dimension screenDimension) {
        super();
        this.setPreferredSize(screenDimension);
        this.setFocusable(true);
        this.requestFocus();
    }

}
