package bwyap.familyfeud.game.state;

import bwyap.utility.statemachine.State;

/**
 * A template for a game state in Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFState extends State {
	
	private FFStateType type;
		
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFState(FFStateType type) {
		super(type.toString());
		this.type = type;
	}
	
	/**
	 * Get the state type.
	 * @return
	 */
	public FFStateType getType() {
		return type;
	}
	
	/**
	 * Execute an action for that state.
	 * Each state should specify a set of valid actions and appropriate action handlers.
	 * If the specified action could not be executed, the method will return false.
	 * @param action
	 * @param data
	 * @return true if the action was successfully executed.
	 */
	public boolean executeAction(int action, Object[] data) {
		return false;
	}

	@Override
	public void updateState(float timeElapsed) {
		// This method is not needed for this state machine
	}
	
}
