package verse.engine.graphics;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * A Frame to start the game and allows for custom content presentation (similar to custom user defined graphics). The 
 * Frame supports most well known resolutions. Currently only fixes to given dimensions.
 * 
 * @author Faiz Gull Chaudhry
 */
@SuppressWarnings("serial")
public class VFrame extends JFrame{

    public static final Dimension STANDARD = new Dimension(640,480), HD = new Dimension(1280, 720), FULL_HD = new Dimension(1920,1080),
        QUAD_HD = new Dimension(2560, 1440), ULTRA_HD = new Dimension(3840, 2160);
        
    public VFrame(String frameTitle, Dimension dimension) {
        super(frameTitle);
        this.setSize(dimension);
        this.init();
    }
        
    public VFrame(String frameTitle, Dimension dimension, Container contentPane) {
        super(frameTitle);
        this.setContentPane(contentPane);
        this.pack();
        this.init();
    }
        
    private void init() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
	
	
}
