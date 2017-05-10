package bwyap.familyfeud.game;

import java.util.List;

import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
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
	
	private Family winner;
	
	/**
	 * Initialize the game
	 */
	public void init() {
		families = new FamilyCollection();
		questions = new QuestionSet();
		winner = null;
		
		stateMachine = new FFStateMachine(this, families, questions);
		stateMachine.init();
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("Game initialized.");
	}
	
	/**
	 * Change the state of the game.
	 * State changes must be validated by the state machine to work.
	 * @param type
	 * @return true if the state transition was successful
	 */
	public boolean changeState(FFStateType type) {
		return stateMachine.changeState(type.toString());
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
	
	/**
	 * Set the winner of the game. Assuming that the game has finished,
	 * this method will set the winner as the family with the highest points.
	 */
	public void setWinner() {
		int index = 0;
		List<Family> families = getFamilies();
		for(int i = 0; i < families.size(); i++) {
			if (families.get(i).getPoints() > families.get(index).getPoints()) {
				index = i;
			}
		}
		winner = families.get(index);
	}
	
	/**
	 * Get the winning family as set by {@code setWinner}.
	 * This will return null if the method {@code setWinnder} has not been run.
	 * @return
	 */
	public Family getWinningFamily() {
		return winner;
	}
	
}
