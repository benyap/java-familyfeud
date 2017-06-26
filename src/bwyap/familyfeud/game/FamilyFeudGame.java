package bwyap.familyfeud.game;

import java.util.List;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.game.state.FFStateMachine;
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
	private FastMoney fastmoney;
	
	private Family winner;
	
	/**
	 * Initialize the game
	 */
	public void init() {
		families = new FamilyCollection();
		questions = new QuestionSet();
		fastmoney = new FastMoney();
		winner = null;
		
		stateMachine = new FFStateMachine(this, families, questions);
		stateMachine.init();
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("Game initialized.");
	}
	
	/**
	 * Reset the game
	 */
	public void reset() {
		families.reset();
		questions.reset();
		winner = null;
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.info("Game reset.");
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
	 * Get the Fast Money instance 
	 * @return
	 */
	public FastMoney getFastMoney() {
		return fastmoney;
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
	private void setWinner() {
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
	 * Automatically calls the {@code setWinnder} method.
	 * @return
	 */
	public Family getWinningFamily() {
		setWinner();
		return winner;
	}

	/**
	 * Check if the game is finished
	 * @return
	 */
	public boolean finished() {
		return winner != null;
	}
	
}
