package verse.engine.ai;

/**
 * A simplified interface that represents a logical AI within the game. Naturally all AI's need to be
 * able to think of their next move or choice within the game.
 * 
 * @author Faiz Chaudhry
 */
public interface ILogicAI {

    /**
     * When called the logic for the AI is done and it readys it's next move/choice.
     */
    public void think();
        
}
