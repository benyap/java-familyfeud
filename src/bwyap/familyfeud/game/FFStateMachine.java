package bwyap.familyfeud.game;

import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.game.state.FFStateFactory;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;
import bwyap.utility.statemachine.StateMachine;

/**
 * A state machine that handles {@code FFState} for Family Feud
 * @author bwyap
 *
 */
public class FFStateMachine extends StateMachine<FFState> {
	
	private FamilyFeudGame game;
	private FamilyCollection families;
	private QuestionSet questions;
	
	public FFStateMachine(FamilyFeudGame game, FamilyCollection families, QuestionSet questions) {
		super("FFGame");
		this.game = game;
		this.families = families;
		this.questions = questions;
	}
	
	@Override
	public void init() {
		// Add all states to the machine
		addState(FFStateType.START.toString(), FFStateFactory.getState(FFStateType.START, null));
		addState(FFStateType.NEW_GAME.toString(), FFStateFactory.getState(FFStateType.NEW_GAME, game));
		addState(FFStateType.ADD_FAMILY.toString(), FFStateFactory.getState(FFStateType.ADD_FAMILY, families));
		addState(FFStateType.LOAD_QUESTIONS.toString(), FFStateFactory.getState(FFStateType.LOAD_QUESTIONS, questions));
		addState(FFStateType.INITIALIZE_GAME.toString(), FFStateFactory.getState(FFStateType.INITIALIZE_GAME, null));
		addState(FFStateType.PLAY.toString(), FFStateFactory.getState(FFStateType.PLAY, game));
		addState(FFStateType.END_GAME.toString(), FFStateFactory.getState(FFStateType.END_GAME, null));
		
		// Set the initial state
		changeState(FFStateType.START.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("FFStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch (currentState.getType()) {
		case ADD_FAMILY:
		case LOAD_QUESTIONS:
			if (nextState == FFStateType.ADD_FAMILY.toString()) return true;
			if (nextState == FFStateType.LOAD_QUESTIONS.toString()) return true;
			if (nextState == FFStateType.INITIALIZE_GAME.toString()) {
				// Check that questions are loaded and there are enough families
				if (families.getFamilies().size() > 1) {
					if (questions.getQuestions().size() > 0) {
						return true;						
					}
					else Logger.err("No questions loaded!");
				}
				else Logger.err("More families required!");
			}
			break;
		case NEW_GAME:
			if (nextState == FFStateType.ADD_FAMILY.toString()) return true;
			if (nextState == FFStateType.LOAD_QUESTIONS.toString()) return true;
			break;
		case END_GAME:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
			break;
		case INITIALIZE_GAME:
			if (nextState == FFStateType.PLAY.toString()) return true;
			break;
		case PLAY:
			if (nextState == FFStateType.END_GAME.toString()) return true;
			break;
		case START:
			if (nextState == FFStateType.NEW_GAME.toString()) return true;
			break;
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
	
	@Override
	protected void printLog(String message) {
		Logger.info(message);
	}
	
	@Override
	protected void printTransition(String message) {
		Logger.warning(message);
	}
	
	@Override
	protected void printErr(String message) {
		Logger.err(message);
	}
	
}
