package bwyap.familyfeud.game.state;

import bwyap.utility.statemachine.State;

/**
 * A template for a game state in Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFState extends State<FFStateType> {
			
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFState(FFStateType type) {
		super(type);
	}

	@Override
	public void updateState(float timeElapsed) {
		// This method is not needed for this state machine
	}
	
}
