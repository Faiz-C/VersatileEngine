package verse.engine.dialogue;

import java.util.List;

/**
 * Represents a single piece of dialogue seen within an event. A DialoguePiece simply knows who is speaking
 * and what they are saying.
 *
 * @author Faiz Chaudhry
 */
public class DialoguePiece {
        
    private String speaker;
    private List<String> dialogue;
        
    public DialoguePiece(String speaker, List<String> dialogue) {
        this.speaker = speaker;
        this.dialogue = dialogue;
    }

    /**
     * Sets the name of the speaker to the given speaker name.
     *
     * @param speaker -> The name of the speaker for the DialoguePiece
     */
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    /**
     * Sets the dialogue (what the speaker is saying) to the given list of lines.
     *
     * @param dialogue -> A list of lines which will act as the new dialogue for the DialoguePiece
     */
    public void setDialogue(List<String> dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * @return The name of the current speaker saying the DialoguePiece
     */
    public String getSpeaker() {
        return this.speaker;
    }

    /**
     * @return The dialogue or lines of the DialoguePiece
     */
    public List<String> getDialogue() {
        return this.dialogue;
    }

    /**
     * @param lineNumber -> The number of the specific line within the list of dialogue
     * @return The line associated with the given line number
     */
    public String getDialogueLine(int lineNumber) {
        return this.dialogue.get(lineNumber);
    }

    /**
     * @return The number of lines for the dialogue within the DialoguePiece
     */
    public int getDialogueLength() {
        return this.dialogue.size();
    }
        
    @Override
    public String toString() {
        String str = this.getSpeaker() + "\n";
        for (String line : this.getDialogue()) {
            str += "\t" + line.trim() + "\n";
        }
        return str;
    }

}
