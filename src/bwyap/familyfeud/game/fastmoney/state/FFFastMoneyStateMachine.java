package bwyap.familyfeud.game.fastmoney.state;

import bwyap.familyfeud.game.AbstractFFStateMachine;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P1_ANSWER;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P1_REVEAL;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_ANSWER;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_REVEAL;

/**
 * A state machine that handles {@code FFFastMoneyState} for Family Feud.
 * This state machine is used within the {@code FFStateType.FAST_MONEY} state.
 * @author bwyap
 *
 */
public class FFFastMoneyStateMachine extends AbstractFFStateMachine<FFFastMoneyState> {
	
	private FastMoney fastmoney;
	
	public FFFastMoneyStateMachine(FastMoney fastmoney) {
		super("FFGame.FastMoney");
		this.fastmoney = fastmoney;
	}

	@Override
	public void init() {
		// Add all states to the machine
		addState(P1_ANSWER.toString(), FFFastMoneyStateFactory.getState(P1_ANSWER, fastmoney));
		addState(P1_REVEAL.toString(), FFFastMoneyStateFactory.getState(P1_REVEAL, fastmoney));
		addState(P2_ANSWER.toString(), FFFastMoneyStateFactory.getState(P2_ANSWER, fastmoney));
		addState(P2_REVEAL.toString(), FFFastMoneyStateFactory.getState(P2_REVEAL, fastmoney));

		// Set the initial state
		fastmoney.reset();
		changeState(P1_ANSWER.toString());

		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("FFFastMoneyStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFFastMoneyState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch(currentState.getType()) {
		case P1_ANSWER:
			if (fastmoney.allAnswered(0) && nextState == P1_REVEAL.toString()) return true;
		case P1_REVEAL:
			return nextState == P2_REVEAL.toString();
		case P2_ANSWER:
			if (fastmoney.allAnswered(1) && nextState == P2_REVEAL.toString()) return true;
		case P2_REVEAL:
			// No more states to transition into
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
