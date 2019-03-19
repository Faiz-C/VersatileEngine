package verse.engine.dialogue;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import verse.engine.animation.Animation;
import verse.engine.animation.TextAnimation;
import verse.engine.updatable.IUpdatable;
import verse.engine.graphics.IDrawable;

/**
 * Representation of an event of which features dialogue. The DialogueEvent will take a DialoguePiece and animate it
 * via TextAnimation. Simply put, it allows a DialoguePiece to be drawn onto the screen.
 *
 * Note: This is still an unfinished feature of the VerseEngine
 *
 * @author Faiz Chaudhry
 */
public class DialogueEvent implements IUpdatable, IDrawable{

    private DialoguePiece eventDialogue;
    private List<Animation> animatedDialogue;
    private int currentLine;
        
    public DialogueEvent(DialoguePiece eventDialogue) {
        this.eventDialogue = eventDialogue;
        this.createAnimatedDialogue();
    }
        
    private void createAnimatedDialogue() {
        this.animatedDialogue = new ArrayList<Animation>();
        for(int n = 0; n < this.eventDialogue.getDialogueLength(); n++) {
            this.animatedDialogue.add(new TextAnimation(this.eventDialogue.getDialogueLine(n), 2, Animation.SINGLE_LOOP));
        }
    }
        
    public void draw(Graphics2D g, int x, int y) {
        FontMetrics metrics = g.getFontMetrics();
        for (int lineNum = 0; lineNum < this.eventDialogue.getDialogueLength(); lineNum++) {
            this.animatedDialogue.get(lineNum).draw(g, x, y + (lineNum * metrics.getHeight()) + 2);
        }
    }

    public void update() {
        if (this.animatedDialogue.get(this.currentLine).isDoneAnimation() && (this.currentLine < this.eventDialogue.getDialogueLength() - 1)) {
            this.currentLine++;
        }
        this.animatedDialogue.get(this.currentLine).update();
    }
	
}
