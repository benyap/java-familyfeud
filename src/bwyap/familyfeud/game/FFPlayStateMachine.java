package bwyap.familyfeud.game;

import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.familyfeud.game.play.state.FFPlayStateFactory;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.statemachine.StateMachine;

public class FFPlayStateMachine extends StateMachine<FFPlayState> {
	
	public FFPlayStateMachine() {
		super("FFGame.Play");
	}

	@Override
	public void init() {
		// Add all states to the machine
		for(FFPlayStateType type : FFPlayStateType.values()) {
			addState(type.toString(), FFPlayStateFactory.getState(type, null));				
			if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("  Adding " + type + " state to " + getName() + " state machine.");
		}
		
		// Set the initial state
		changeState(FFPlayStateType.SELECT_QUESTION.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("FFPlayStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFPlayState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch(currentState.getType()) {
		case ALLOCATE_POINTS:
			if (nextState == FFPlayStateType.REVEAL_ANSWERS.toString()) return true;
		case FACE_OFF:
			if (nextState == FFPlayStateType.FAMILY_PLAY.toString()) return true;
		case FAMILY_PLAY:
			// TODO
			if (nextState == FFPlayStateType.FAMILY_STEAL.toString()) return true;
			if (nextState == FFPlayStateType.ALLOCATE_POINTS.toString()) return true;
		case FAMILY_STEAL:
			if (nextState == FFPlayStateType.ALLOCATE_POINTS.toString()) return true;
		case REVEAL_ANSWERS:
			// TODO
			if (nextState == FFPlayStateType.SELECT_QUESTION.toString()) return true;
		case SELECT_QUESTION:
			if (nextState == FFPlayStateType.FACE_OFF.toString()) return true;
		}
		
		return false;
	}
	
	/**
	 * Get the state type of the current state
	 * @return
	 */
	public FFPlayStateType getCurrentStateType() {
		if (currentState == null) {
			return null;
		}
		else return currentState.getType();
	}
	
	@Override
	protected boolean getDebugMode() {
		// Return the appropriate debug variable
		return FamilyFeudTestDriver.DEBUG_LOG_CONSOLE;
	}
	
}
