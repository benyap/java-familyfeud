package bwyap.familyfeud.game.fastmoney.state;

import bwyap.utility.statemachine.State;

/**
 * Represents a state in the FAST_MONEY state of a game of Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public class FFFastMoneyState extends State {
	
	private FFFastMoneyStateType type;
	
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFFastMoneyState(FFFastMoneyStateType type) {
		super(type.toString());
		this.type = type;
	}

	/**
	 * Get the state type.
	 * @return
	 */
	public FFFastMoneyStateType getType() {
		return type;
	}
	
	@Override
	public void initState(Object data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateState(float timeElapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanupState() {
		// TODO Auto-generated method stub
		
	}
	
}
