package bwyap.familyfeud.game.state;

import bwyap.statemachine.State;

/**
 * A template for a game state in Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFState extends State {
	
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	public FFState(String name) {
		super(name);
	}

}
