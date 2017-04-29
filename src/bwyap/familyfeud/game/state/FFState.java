package bwyap.familyfeud.game.state;

import bwyap.statemachine.State;

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

}
