package bwyap.familyfeud.game.play.state;

import bwyap.utility.statemachine.State;

/**
 * Represents a state during the PLAY phase of a game of Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFPlayState extends State<FFPlayStateType> {
			
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFPlayState(FFPlayStateType type) {
		super(type);
	}

	@Override
	public void updateState(float timeElapsed) {
		// This method is not needed for this state machine
	}
	
}
