package manualTesting.inputProcessing;

import java.awt.Dimension;

import javax.swing.JFrame;

import verse.engine.IEngineCog;
import verse.engine.inputProcessing.ActionManager;
import verse.engine.inputProcessing.IInputProcessor;
import verse.engine.inputProcessing.IInputTranslator;
import verse.engine.inputProcessing.InputProcessingCog;

@SuppressWarnings("serial")
public class InputTesting extends JFrame{
	
	private IEngineCog inputProcessingComponent;
	private ActionManager actionManager;

	public InputTesting() {
		super("Testing Input");
		this.setSize(new Dimension(200, 200));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		IInputProcessor inputProcessor = new BasicInputProcessor();
		IInputTranslator inputTranslator = new KeyInputTranslator();
		this.actionManager = new ActionManager();
		
		this.actionManager.modifyGameAction(KeyInputTranslator.UP, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.DOWN, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.LEFT, false);
		this.actionManager.modifyGameAction(KeyInputTranslator.RIGHT, false);
		
		this.inputProcessingComponent = new InputProcessingCog(inputProcessor);
		
		this.addKeyListener(new KeyInputListener(inputTranslator, this.actionManager));
		
		this.setVisible(true);
		
		this.doSomething();
	}
	
	private void doSomething() {
		while(true) {		
			this.inputProcessingComponent.initTurn(this.actionManager);	
			this.inputProcessingComponent.turnCog();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		new InputTesting();
	}
	
}
