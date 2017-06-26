package bwyap.familyfeud.game.fastmoney.state;

import bwyap.familyfeud.game.AbstractFFStateMachine;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * A state machine that handles {@code FFFastMoneyState} for Family Feud.
 * This state machine is used within the {@code FFStateType.FAST_MONEY} state.
 * @author bwyap
 *
 */
public class FFFastMoneyStateMachine extends AbstractFFStateMachine<FFFastMoneyState> {
	
	public FFFastMoneyStateMachine() {
		super("FFGame.FastMoney");
	}

	@Override
	public void init() {
		// Add all states to the machine
		
		// Set the initial state
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("FFFastMoneyStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFFastMoneyState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch(currentState.getType()) {
		
		}
		
		return false;
	}
	
	/**
	 * Get the state type of the current state
	 * @return
	 */
	public FFFastMoneyStateType getCurrentStateType() {
		if (currentState == null) {
			return null;
		}
		else return currentState.getType();
	}
	
}
