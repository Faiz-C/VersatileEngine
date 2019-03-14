package verse.engine;

import java.util.List;
import java.util.ArrayList;

import verse.engine.inputProcessing.*;
import verse.engine.updatable.*;
import verse.engine.graphics.*;
import verse.engine.sound.*;
import verse.engine.utils.*;

/**
 * This is the standard version of the VersatileEngine. This is a pre-built version which can be used right away utilizing the
 * the pre-constructed engine cogs, however, it can still be added upon. The engine should be run in it's own thread to avoid
 * any conflicts.
 * 
 * The main restriction with using this version is that the main components (graphics, updating, input and sound) are all fixed.
 *
 * @author Faiz Chaudhry
 */
public class StandardVersatileEngine implements Runnable{

    public static enum CogType {INPUT, UPDATE, GRAPHICS};

    // We group the component types together to properly retain order of execution of
    // the components.
    private List<IEngineCog> inputCogs, updateCogs, graphicCogs;
    
    // For Input
    private IInputProcessor inputProcessor;
    private List<IInputTranslator> inputTranslators;
    
    // For Updating
    private List<IUpdatable> objectsToUpdate;
    
    // For Graphics
    private BufferedImage gameImage;
    private Graphics2D gameGraphics;

    // For Sound
    private SoundManager soundManager;

    // For the Game Loop
    private long fps;
    
    public StandardVersatileEngine() {
        this.initComponents();
    }

    private void initComponents() {
        this.setCogs(new ArrayList<IEngineCog>(), INPUT);
        this.setCogs(new ArrayList<IEngineCog>(), UPDATE);
        this.setCogs(new ArrayList<IEngineCog>(), GRAPHICS);
    }

    private void updateObjects() {

        for (IUpdatable uObject : this.objectsToUpdate) {
            uObject.update();
        }
        
    }

    /*
     * Below are methods to help setup input processing
     */

    public void setInputProcessor(IInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public void setInputTranslators(List<IInputTranslator> inputTranslators){
        this.inputTranslators = inputTranslators;
    }

    public void addInputTranslator(IInputTranslator inputTranslator) {
        this.inputTranslator.append(inputTranslator);
    }

    public void removeInputTranslator(IInputTranslator inputTranslator) {
        this.inputTranslator.remove(inputTranslator);
    }

    
    /*
     * Below are methods for the engine cogs themselves
     */

    public void setCogs(List<IEngineCog> cogs, CogType type) {

        switch(type) {

        case INPUT:
            this.inputCogs = cogs;
            break;

        case UPDATE:
            this.updateCogs = cogs;
            break;

        case GRAPHICS:
            this.graphicCogs = cogs;
            break;

        default:
            System.out.println("Invalid CogType");
            break;
        }
        
    }

    public void addCog(IEngineCog cog, CogType type) {

        switch(type) {

        case INPUT:
            this.inputCogs.append(cog);
            break;

        case UPDATE:
            this.updateCogs.append(cog);
            break;

        case GRAPHICS:
            this.graphicCogs.append(cog);
            break;

        default:
            System.out.println("Invalid CogType");
            break;
        }

    }

    public void removeCog(IEngineCog cog, CogType type) {

        try {
        
            switch(type) {

            case INPUT:
                this.inputCogs.remove(cog);
                break;

            case UPDATE:
                this.updateCogs.remove(cog);
                break;

            case GRAPHICS:
                this.graphicCogs.remove(cog);
                break;

            default:
                System.out.println("Invalid CogType");
                break;
            }
            
        } catch(Exeception e) {
            ErrorHandler.raiseError("Cog not found", e);
        }

    }
   
    @Override
    public void run() {
        long start, elapsed, wait;

        while(true) {

            start = System.nanoTime();

            elapsed = System.nanoTime() - start;
            wait =  this.fps - elapsed / 1000000;

            // Wait a bit before looping again
            if (wait < 0) wait = 5; // We can't wait negative time, fix it to 5 if it happens

            try{ Thread.sleep(wait); }
            catch(Exception e) { 
                ErrorHandler.raiseError("Could not properly sleep during game loop", e);
            }

        }
 
    }

    
    
}
