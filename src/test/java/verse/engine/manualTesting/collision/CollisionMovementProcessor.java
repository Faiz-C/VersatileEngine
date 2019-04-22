package verse.engine.manualTesting.collision;

import verse.engine.manualTesting.inputProcessing.KeyInputTranslator;
import verse.engine.inputProcessing.ActionManager;
import verse.engine.inputProcessing.IInputProcessor;
import verse.engine.physics.Coordinate;
import verse.engine.utils.ErrorHandler;


public class CollisionMovementProcessor implements IInputProcessor{
	
	private TestObject obj1, obj2;
	private final int SHIFT = 2;
	
	public CollisionMovementProcessor(TestObject obj1, TestObject obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
		
	}

	public void processInput(ActionManager actionManager) {
		
		Coordinate org = new Coordinate(this.obj1.getPosition().getX(), this.obj1.getPosition().getY());
		
		try {
		
			if (actionManager.isPerforming(KeyInputTranslator.DOWN)) {
				this.obj1.setPosition(this.obj1.getPosition().getX(), this.obj1.getPosition().getY() + SHIFT);
			}
			
			else if (actionManager.isPerforming(KeyInputTranslator.UP)) {
				this.obj1.setPosition(this.obj1.getPosition().getX(), this.obj1.getPosition().getY() - SHIFT);
			}
			
			else if (actionManager.isPerforming(KeyInputTranslator.RIGHT)) {
				this.obj1.setPosition(this.obj1.getPosition().getX() + SHIFT, this.obj1.getPosition().getY());
			}
			
			else if (actionManager.isPerforming(KeyInputTranslator.LEFT)) {
				this.obj1.setPosition(this.obj1.getPosition().getX() - SHIFT, this.obj1.getPosition().getY());
			}
			
			// If collision happens revert
			if (this.obj1.hasCollided(this.obj2)) {
				System.out.println(org.getX() + " : " + org.getY());	
				System.out.println(this.obj1.getPosition().getX() + " : " + this.obj1.getPosition().getY());
				this.obj1.setPosition(org.getX(), org.getY());
			}
			
		} catch(Exception e) {
			ErrorHandler.raiseError("Failed to ", e);
		}
	}

}
