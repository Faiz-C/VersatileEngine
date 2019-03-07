package verse.engine.graphics;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * A very simple container used for drawing onto. It doesn't do much else but can be added
 * onto.
 * 
 * @author Faiz Gull Chaudhry
 */
@SuppressWarnings("serial")
public class DrawScreen extends JPanel{
        
    public DrawScreen(Dimension screenDimension) {
        super();
        this.setPreferredSize(screenDimension);
        this.setFocusable(true);
        this.requestFocus();
    }

}
