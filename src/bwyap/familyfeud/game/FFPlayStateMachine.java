package bwyap.familyfeud.game;

import bwyap.familyfeud.game.play.state.FFPlayState;
import bwyap.familyfeud.game.play.state.FFPlayStateFactory;
import bwyap.familyfeud.game.play.state.FFPlayStateType;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * A state machine that handles {@code FFPlayState} for Family Feud.
 * This state machine is used within the {@code FFStateType.PLAY} state.
 * @author bwyap
 *
 */
public class FFPlayStateMachine extends AbstractFFStateMachine<FFPlayState> {
	
	private QuestionSet questions;
	private FamilyCollection families;
	
	public FFPlayStateMachine(QuestionSet questions, FamilyCollection families) {
		super("FFGame.Play");
		this.questions = questions;
		this.families = families;
	}

	@Override
	public void init() {
		// Add all states to the machine
		addState(FFPlayStateType.SELECT_QUESTION.toString(), FFPlayStateFactory.getState(FFPlayStateType.SELECT_QUESTION, questions));				
		addState(FFPlayStateType.FACE_OFF.toString(), FFPlayStateFactory.getState(FFPlayStateType.FACE_OFF, questions));				
		addState(FFPlayStateType.FAMILY_PLAY.toString(), FFPlayStateFactory.getState(FFPlayStateType.FAMILY_PLAY, null));				
		addState(FFPlayStateType.FAMILY_STEAL.toString(), FFPlayStateFactory.getState(FFPlayStateType.FAMILY_STEAL, null));				
		addState(FFPlayStateType.ALLOCATE_POINTS.toString(), FFPlayStateFactory.getState(FFPlayStateType.ALLOCATE_POINTS, families));				
		addState(FFPlayStateType.REVEAL_ANSWERS.toString(), FFPlayStateFactory.getState(FFPlayStateType.REVEAL_ANSWERS, null));				
		
		// Set the initial state
		changeState(FFPlayStateType.SELECT_QUESTION.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("FFPlayStateMachine initialized.");
	}
	
	@Override
	public boolean validateStateTransition(FFPlayState currentState, String nextState) {
		if (currentState == null) return true;
		
		switch(currentState.getType()) {
		case ALLOCATE_POINTS:
			if (nextState == FFPlayStateType.REVEAL_ANSWERS.toString()) return true;
			break;
		case FACE_OFF:
			if (nextState == FFPlayStateType.FAMILY_PLAY.toString()) return true;
			break;
		case FAMILY_PLAY:
			if (nextState == FFPlayStateType.FAMILY_STEAL.toString()) return true;
			if (nextState == FFPlayStateType.ALLOCATE_POINTS.toString()) return true;
			break;
		case FAMILY_STEAL:
			if (nextState == FFPlayStateType.ALLOCATE_POINTS.toString()) return true;
			break;
		case REVEAL_ANSWERS:
			if (nextState == FFPlayStateType.SELECT_QUESTION.toString()) return true;
			break;
		case SELECT_QUESTION:
			if (nextState == FFPlayStateType.FACE_OFF.toString()) return true;
			break;
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
	
}
