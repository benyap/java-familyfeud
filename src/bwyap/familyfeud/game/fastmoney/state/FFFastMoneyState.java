package bwyap.familyfeud.game.fastmoney.state;

import bwyap.utility.statemachine.State;

/**
 * Represents a state in the FAST_MONEY state of a game of Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFFastMoneyState extends State<FFFastMoneyStateType> {
		
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFFastMoneyState(FFFastMoneyStateType type) {
		super(type);
	}

	@Override
	public void updateState(float timeElapsed) {
		// This method is not needed for this state machine
	}
	
}
