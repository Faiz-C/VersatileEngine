package verse.engine.utils;

public class ErrorHandler {
        
    // Simply logs and displays errors to the user
    public static void raiseError(String errorMessage, Exception e) {
        System.err.println(errorMessage);		
        if (e != null) {
            System.err.println("Error Raised: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
