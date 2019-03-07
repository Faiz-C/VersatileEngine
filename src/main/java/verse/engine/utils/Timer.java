package verse.engine.utils;

import verse.engine.updatable.IUpdatable;

/**
 * An Observable Timer which allows parts of the engine to work based off a time trigger. When the time trigger
 * is hit all the Observers update. The Timer needs to be started to work and can be stopped if needed. 
 * 
 * @author Faiz Gull Chaudhry
 */
public class Timer extends Observable implements IUpdatable{
        
    private int frameCounter, frameTrigger;
    private boolean onTime;
        
    public Timer(int frameTrigger) {
        this.frameCounter = 0;
        this.frameTrigger = frameTrigger;
    }
        
    /**
     * Starts the Timer
     */
    public void start() {
        this.onTime = true;
        this.frameCounter = 0;
    }
        
    /**
     * Reset the internal counter of the timer. DOES NOT START THE TIMER!
     */
    public void reset() {
        this.frameCounter = 0;
    }
        
    /**
     * Stop the Timer
     */
    public void stop() {
        this.onTime = false;
    }

    public void update() {
                
        if (!onTime) return;
                
        this.frameCounter++;
        if (this.frameCounter >= this.frameTrigger) {
            this.notifyObservers();
            this.frameCounter = 0;
        }
    }

}
