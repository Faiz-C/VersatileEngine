package verse.engine.utils;

import java.awt.FontMetrics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This handler just helps with dealing with dialogue from files, and formatting text to fit onto the screen.
 *
 * @author Faiz Chaudhry
 */
public class DialogueHandler {

    /**
     * Takes a single full message (like a sentence) and splits it into smaller messages based spaces, each of which 
     * will fit on a screen with the given width.
     *
     * @param fullMessage -> The complete message wanting to be split 
     * @param metrics -> The FontMetrics for the graphics object being used
     * @param screenWidth -> The width of the visible screen
     */
    public static List<String> splitTextForScreen(String fullMessage, FontMetrics metrics, int screenWidth) {
                
        String[] words = fullMessage.split(" ");
        List<String> dialogueLines = new ArrayList<String>();
        String line = "";

        for (String word : words) {
            String sentence = line + " " + word;
            if (metrics.stringWidth(sentence) > screenWidth) {
                dialogueLines.add(line);
                line = "";
            }
            line += " " + word;
        }	
		
        dialogueLines.add(line); // Add the last line in
		
        return dialogueLines;
    }
	
}
