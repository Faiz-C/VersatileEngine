package verse.engine.animation;

import java.awt.Graphics2D;

import verse.engine.updatable.IUpdatable;
import verse.engine.utils.IObserver;
import verse.engine.utils.Timer;

/**
 * An abstraction for Animations within the VersatileEngine. Animations are kept updated based off
 * a Timer, however the timer can be forced on or off as needed. However, drawing is left up to
 * implementation.
 * 
 * @author Faiz Chaudhry
 */
public abstract class Animation implements IUpdatable, IObserver{

    // Constants
    public static final int NO_DELAY = 1, STANDARD_DELAY = 4, DOUBLED_DELAY = 8, LONG_DELAY = 20;
    public static final int SINGLE_LOOP = 1, INFINITE_LOOP = -1;

    protected int loopCount, frameTrigger;
    private Timer timer;
        
    protected int currentAnimationPosition, maxAnimationPosition;
        
    public Animation(int frameTrigger, int loopCount) {
        this.frameTrigger = frameTrigger;
        this.loopCount = loopCount;
        this.timer = new Timer(frameTrigger);
        this.timer.addObserver(this);
        this.timer.start();
    }

    /**
     * Moves the current animation from forward if able and resets it to the
     * beginning if we are looping still (or infinitely if we want infinite loops).
     */
    private void animate() {
        // Loops the Animation if need be
        if (this.currentAnimationPosition >= this.maxAnimationPosition) {	
            if (this.loopCount == Animation.INFINITE_LOOP) {
                this.currentAnimationPosition = 0;
            } else if (this.loopCount > 0) {
                this.loopCount--;
            }

        } else {
            this.currentAnimationPosition++;
        }
    }
        
    /**
     * Force the Animation's update Timer to start. In other words, start playing
     * the animation.
     */
    public void forceStart() {
        this.timer.start();
    }
        
    /**
     * Force the Animation's update Timer to start. In other words, stop playing
     * the animation.
     */
    public void forceStop() {
        this.timer.stop();
    }
        
    /**
     * Returns whether or not the Animation is finished animating.
     * 
     * @return True if the animation is finished its cycles, false otherwise
     */
    public boolean isDoneAnimation() {
        return this.loopCount == 0;
    }
        
    /**
     * Sets the amount of times the Animation will loop from its beginning.
     * 
     * @param newLoopCount -> The number of times the animation should loop
     */
    public void setLoopCount(int loopCount) {
        this.loopCount = loopCount;
    }
        
    /**
     * Resets the Animation from its current position back to the beginning.
     */
    public void reset() {
        this.currentAnimationPosition = 0;
        this.timer.reset();
    }
        
    public void update() {
        this.timer.update();		
    }
        
    public void update(Object... args) {
        this.animate();
    }       
        
    /**
     * Draws the current sprite which the Animation is on.
     * 
     * @param g -> The Graphics2D object of the container/canvas where the Animation will be drawn
     * @param x -> The x-coordinate to position the Animation
     * @param y -> The y-coordinate to position the Animation
     */
    public abstract void draw(Graphics2D g, int x, int y);	
}
