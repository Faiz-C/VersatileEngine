package verse.engine.dialogue;

import java.util.List;

public class DialoguePiece {
        
    private String speaker;
    private List<String> dialogue;
        
    public DialoguePiece(String speaker, List<String> dialogue) {
        this.speaker = speaker;
        this.dialogue = dialogue;
    }
        
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
        
    public void setDialogue(List<String> dialogue) {
        this.dialogue = dialogue;
    }
        
    public String getSpeaker() {
        return this.speaker;
    }
        
    public List<String> getDialogue() {
        return this.dialogue;
    }
        
    public String getDialogueLine(int lineNumber) {
        return this.dialogue.get(lineNumber);
    }
        
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
