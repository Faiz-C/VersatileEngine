package verse.engine.utils;

/**
 * A simple handler to help with sending feedback to standard error based on the 
 * given message and Exception.
 *
 * @author Faiz Chaudhry
 */
public class ErrorHandler {

    /**
     * Writes the given error message to standard error and then raises the exception along with
     * printing the stack trace which lead to that Exception.
     *
     * @param errorMessage -> The message wanting to be written to standard error
     * @param e -> The actual Exception which was suppose to be raised 
     */
    public static void raiseError(String errorMessage, Exception e) {
        System.err.println(errorMessage);		
        if (e != null) {
            System.err.println("Error Raised: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
