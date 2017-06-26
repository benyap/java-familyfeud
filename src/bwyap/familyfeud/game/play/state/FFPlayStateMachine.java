package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.AbstractFFStateMachine;
import bwyap.familyfeud.game.FamilyCollection;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

import static bwyap.familyfeud.game.play.state.FFPlayStateType.ALLOCATE_POINTS;
import static bwyap.familyfeud.game.play.state.FFPlayStateType.FACE_OFF;
import static bwyap.familyfeud.game.play.state.FFPlayStateType.FAMILY_PLAY;
import static bwyap.familyfeud.game.play.state.FFPlayStateType.FAMILY_STEAL;
import static bwyap.familyfeud.game.play.state.FFPlayStateType.REVEAL_ANSWERS;
import static bwyap.familyfeud.game.play.state.FFPlayStateType.SELECT_QUESTION;

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
		addState(SELECT_QUESTION.toString(), FFPlayStateFactory.getState(SELECT_QUESTION, questions));				
		addState(FACE_OFF.toString(), FFPlayStateFactory.getState(FACE_OFF, questions));				
		addState(FAMILY_PLAY.toString(), FFPlayStateFactory.getState(FAMILY_PLAY, null));				
		addState(FAMILY_STEAL.toString(), FFPlayStateFactory.getState(FAMILY_STEAL, null));				
		addState(ALLOCATE_POINTS.toString(), FFPlayStateFactory.getState(ALLOCATE_POINTS, families));				
		addState(REVEAL_ANSWERS.toString(), FFPlayStateFactory.getState(REVEAL_ANSWERS, null));				
		
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
