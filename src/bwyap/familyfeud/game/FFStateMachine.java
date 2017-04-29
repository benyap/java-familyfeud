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
	
	private FamilyCollection families;
	private QuestionSet questions;
	
	public FFStateMachine(FamilyCollection families, QuestionSet questions) {
		this.families = families;
		this.questions = questions;
	}
	
	@Override
	public void init() {
		// Add all states to the machine
		for(FFStateType type : FFStateType.values()) {
			if (type == FFStateType.ADD_FAMILY) {
				addState(type.toString(), FFStateFactory.getState(type, families));
			}
			else if (type == FFStateType.LOAD_QUESTIONS) {
				addState(type.toString(), FFStateFactory.getState(type, questions));
			}
			else {
				addState(type.toString(), FFStateFactory.getState(type, null));				
			}
			if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("  Adding " + type + " state to state machine.");
		}
		
		// Set the initial state
		changeState(FFStateType.START.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("FFStateMachine initialized.");
	}
	
	/**
	 * Get the current state
	 * @return
	 */
	public FFState getCurrentState() {
		return currentState;
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
			if (nextState == FFStateType.ADD_FAMILY.toString()) return true;
			if (nextState == FFStateType.LOAD_QUESTIONS.toString()) return true;
			if (nextState == FFStateType.INITIALIZE_GAME.toString()) {
				// Check that questions are loaded and there are enough families
				if (families.getFamilies().size() > 1) {
					if (questions.getQuestions().size() > 0) {
						return true;						
					}
					else System.err.println("No questions loaded!");
				}
				else System.err.println("More families required!");
			}
		case PLAY:
			if (nextState == FFStateType.END_GAME.toString()) return true;
		case START:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
		}
		return false;
	}
	
	/**
	 * Get the state type of the current state
	 * @return
	 */
	public FFStateType getCurrentStateType() {
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
