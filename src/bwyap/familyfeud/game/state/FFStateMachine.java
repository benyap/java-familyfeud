package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.AbstractFFStateMachine;
import bwyap.familyfeud.game.FamilyCollection;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

import static bwyap.familyfeud.game.state.FFStateType.ADD_FAMILY;
import static bwyap.familyfeud.game.state.FFStateType.END_GAME;
import static bwyap.familyfeud.game.state.FFStateType.FAST_MONEY;
import static bwyap.familyfeud.game.state.FFStateType.INITIALIZE_GAME;
import static bwyap.familyfeud.game.state.FFStateType.LOAD_QUESTIONS;
import static bwyap.familyfeud.game.state.FFStateType.NEW_GAME;
import static bwyap.familyfeud.game.state.FFStateType.PLAY;
import static bwyap.familyfeud.game.state.FFStateType.START;

/**
 * A state machine that handles {@code FFState} for Family Feud
 * @author bwyap
 *
 */
public class FFStateMachine extends AbstractFFStateMachine<FFState> {
	
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
		addState(START.toString(), FFStateFactory.getState(START, null));
		addState(NEW_GAME.toString(), FFStateFactory.getState(NEW_GAME, game));
		addState(ADD_FAMILY.toString(), FFStateFactory.getState(ADD_FAMILY, families));
		addState(LOAD_QUESTIONS.toString(), FFStateFactory.getState(LOAD_QUESTIONS, questions));
		addState(INITIALIZE_GAME.toString(), FFStateFactory.getState(INITIALIZE_GAME, null));
		addState(PLAY.toString(), FFStateFactory.getState(PLAY, game));
		addState(END_GAME.toString(), FFStateFactory.getState(END_GAME, game));
		addState(FAST_MONEY.toString(), FFStateFactory.getState(FAST_MONEY, null));
		
		// Set the initial state
		changeState(START.toString());
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("FFStateMachine initialized.");
	}
	
	private FFStateType fromFastMoney = null; 
	
	@Override
	public boolean validateStateTransition(FFState currentState, String nextState) {
		if (currentState == null) return true;
				
		switch (currentState.getType()) {
		case ADD_FAMILY:
		case LOAD_QUESTIONS:
			if (nextState == ADD_FAMILY.toString()) return true;
			if (nextState == LOAD_QUESTIONS.toString()) return true;
			if (nextState == INITIALIZE_GAME.toString()) {
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
			if (nextState == ADD_FAMILY.toString()) return true;
			if (nextState == LOAD_QUESTIONS.toString()) return true;
			break;
		case END_GAME:
			if (nextState == NEW_GAME.toString()) return true;
			if (nextState == FAST_MONEY.toString()) {
				fromFastMoney = currentState.getType();
				return true;
			}
			break;
		case INITIALIZE_GAME:
			if (nextState == PLAY.toString()) return true;
			break;
		case PLAY:
			if (nextState == END_GAME.toString()) return true;
			if (nextState == FAST_MONEY.toString()) {
				fromFastMoney = currentState.getType();
				return true;
			}
			break;
		case START:
			if (nextState == NEW_GAME.toString()) return true;
			break;
		case FAST_MONEY:
			// Fast money must transition back into previous state
			if (nextState == fromFastMoney.toString()) return true;
			return true;
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
}
