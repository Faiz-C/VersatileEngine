package verse.engine.state;

import java.util.HashMap;
import java.util.Map;

import verse.engine.utils.Observable;
import verse.engine.exceptions.GameStateAlreadyExistsException;
import verse.engine.exceptions.NonExistentGameStateException;

public class GameStateManager extends Observable{
	
	private Map<String, GameState> gameStates;
	private GameState currentState;
	
	public GameStateManager() {
		this.gameStates = new HashMap<String, GameState>();
		this.currentState = null;
	}
	
	public void addGameState(String stateName, GameState state) throws GameStateAlreadyExistsException {
		if (this.gameStates.containsKey(stateName)) {
			throw new GameStateAlreadyExistsException(stateName);
		}
		this.gameStates.put(stateName, state);
	}
	
	public void removeGameState(String stateName) throws NonExistentGameStateException {
		if (!this.gameStates.containsKey(stateName)) {
			throw new NonExistentGameStateException(stateName);
		}
		this.gameStates.remove(stateName);
	}
	
	public void swapState(String stateName) throws NonExistentGameStateException {
		if (!this.gameStates.containsKey(stateName)) {
			throw new NonExistentGameStateException(stateName);
		}
		
		this.currentState = this.gameStates.get(stateName);
		this.notifyObservers();
	}
	
	public GameState getCurrentState() {
		return this.currentState;
	}

}
