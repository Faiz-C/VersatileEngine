package verse.engine.inputProcessing;

/**
 * The purpose of an InputTranslator is to relate a User's input to an action within the game
 * itself. All InputTranslators need to be able to translate input given some form of User input
 * which they are designed to interrupt.
 * 
 * @author Faiz Chaudhry
 */
public interface IInputTranslator {

    /**
     * Given User input the translator will return the binded action to that
     * input or null if said input is not binded to anything.
     * 
     * @param input -> Some form of User input to be translated
     */
    public String translateInput(Object input);
	
}
