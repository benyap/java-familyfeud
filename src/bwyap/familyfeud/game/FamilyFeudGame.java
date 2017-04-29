package bwyap.familyfeud.game;

import java.util.List;

import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * This class holds information about a Family Feud game, its components and its state.
 * @author bwyap
 *
 */
public class FamilyFeudGame {
	
	public static final int STRIKE_LIMIT = 3;
	
	private FFStateMachine stateMachine;
	private FamilyCollection families;
	private QuestionSet questions;
	
	public void init() {
		families = new FamilyCollection();
		questions = new QuestionSet();
		
		stateMachine = new FFStateMachine(this, families, questions);
		stateMachine.init();
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.log("Game initialized.");
	}
	
	/**
	 * Change the state of the game.
	 * State changes must be validated by the state machine to work.
	 * @param type
	 */
	public void changeState(FFStateType type) {
		stateMachine.changeState(type.toString());
	}
	
	/**
	 * Get the current state of the game
	 * @return
	 */
	public FFState getState() {
		return stateMachine.getCurrentState();
	}
	
	/**
	 * Get the list of families in the game
	 * @return
	 */
	public List<Family> getFamilies() {
		return families.getFamilies();
	}
	
	/**
	 * Get the list of questions in the game
	 * @return
	 */
	public List<Question> getQuestions() {
		return questions.getQuestions();
	}

	/**
	 * Get the question set object in the game
	 * @return
	 */
	public QuestionSet getQuestionSet() {
		return questions;
	}

	/**
	 * Get the family collection object in the game
	 * @return
	 */
	public FamilyCollection getFamilyCollection() {
		return families;
	}
	
}
