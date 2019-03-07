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

public class DialogueHandler {

    public static void appendToFile(JSONObject jObject, String filePath) {
        try {
                        
            File file = new File(filePath);
                        
            // Check if file exists and is writable
                        
            if (!file.exists() || !file.canWrite()) {
                ErrorHandler.raiseError("File either doesn't exist or cannot be written to", null);
                return;
            }
                        
            // Write to file
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        
            bufferedWriter.append(jObject.toJSONString());
                        
            bufferedWriter.close();
            fileWriter.close();
                        
        } catch(Exception e) {
            ErrorHandler.raiseError("Could not write JSON Object to given file", e);
        }
    }
        
    // Incomplete
    public static void loadFromFile(String filePath) {
        try {
            File file = new File(filePath);
                        
            // Check if file exists and is writable
                        
            if (!file.exists() || !file.canRead()) {
                ErrorHandler.raiseError("File either doesn't exist or cannot be written to", null);
                return;
            }
                        
            Object obj = new JSONParser().parse(new FileReader(file));
                        
            JSONObject jObject = (JSONObject) obj;
                        
            JSONArray dialogue = (JSONArray) jObject.get("Dialogue");
                        
            for (Object jObj : dialogue) {
                JSONObject speech = (JSONObject) jObj;
                //DialoguePiece dialoguePiece = new DialoguePiece((String)speech.get("Speaker"), DialogueHandler(String)speech.get("Speech"));
            }
                        
                        
        } catch(Exception e) {
            ErrorHandler.raiseError("Could not read JSON from file", e);
        }
    }
        
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
