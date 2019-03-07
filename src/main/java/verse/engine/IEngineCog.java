package verse.engine;


/**
 * An EngineCog is an abstraction for any piece wanting to be layered into the
 * VerseEngine.
 * 
 * @author Faiz Gull Chaudhry
 */
public interface IEngineCog {

    /**
     * Initializes one turn of the EngineCog. Before an EngineCog can turn it may
     * have to initialize certain aspects to turn properly.
     * 
     * @param args - An assortment of needed arguments which the cog needs to run
     */
    public void initTurn(Object... args);
        
    /**
     * Turns the EngineCog ONCE. In other words, runs one cycle of what the EngineCog
     * is designed to do.
     */
    public void turnCog();
	
}
