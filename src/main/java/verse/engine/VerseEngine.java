package verse.engine;


// Want three main accessible componenents. One for updating, graphics, and input

public class VerseEngine {

    // Static instance for singleton
    private static VerseEngine instance;

    private IEngineCog input, rendering, updating;    
    private VerseEngine() {

    }

    public static synchronized VerseEngine getInstance() {
        if (instance == null) {
            instance = new VerseEngine();
        }

        return instance;
    }
    
}
