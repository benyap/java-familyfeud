package bwyap.familyfeud.game;

import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.game.state.FFStateFactory;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.statemachine.StateMachine;

/**
 * A state machine that handles {@code FFState} for Family Feud
 * @author bwyap
 *
 */
public class FFStateMachine extends StateMachine<FFState> {
	
	@Override
	public void init() {
		// Add all states to the machine
		for(FFStateType type : FFStateType.values()) {
			addState(type.toString(), FFStateFactory.getState(type));
			if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("  Adding " + type + " state to state machine.");
		}
		
		// Set the initial state
		changeState(FFStateType.START.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("FFStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch (currentState.getType()) {
		case ADD_FAMILY:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
		case END_GAME:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
		case INITIALIZE_GAME:
			if (nextState == FFStateType.PLAY.toString()) return true;
		case LOAD_QUESTIONS:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
		case NEW_GAME:
			if (nextState == FFStateType.INITIALIZE_GAME.toString()) return true;
			if (nextState == FFStateType.ADD_FAMILY.toString()) return true;
			if (nextState == FFStateType.LOAD_QUESTIONS.toString()) return true;
		case PLAY:
			if (nextState == FFStateType.END_GAME.toString()) return true;
		case START:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
		}
		return false;
	}
	
	@Override
	protected boolean getDebugMode() {
		// Return the appropriate debug variable
		return FamilyFeudTestDriver.DEBUG_LOG_CONSOLE;
	}
	
}
