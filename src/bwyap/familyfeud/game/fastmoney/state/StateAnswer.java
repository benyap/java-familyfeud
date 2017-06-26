package bwyap.familyfeud.game.fastmoney.state;

import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P1_ANSWER;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P1_REVEAL;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_ANSWER;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_REVEAL;

import bwyap.familyfeud.game.InvalidDataException;

/**
 * This state represents when a player is giving answers in Fast Money.
 * @author bwyap
 *
 */
public class StateAnswer extends FFFastMoneyState {

	public static final int ACTION_UPDATEANSWER = 0x10;
	public static final int ACTION_UPDATESCORE = 0x11;

	private final int PLAYER;
	private FastMoney fastmoney;
	
	public StateAnswer(FastMoney fastmoney, int player) {
		super(player == 0 ? P1_ANSWER : (player == 1 ? P2_ANSWER : null));
		this.fastmoney = fastmoney;
		this.PLAYER = player;
	}

	@Override
	public void initState(Object data) { }

	@Override
	public void cleanupState() { }
	
	@Override
	public boolean canAdvance(String nextState) {
		if (PLAYER == 0) {
			return nextState == P1_REVEAL.toString();
		}
		else if (PLAYER == 1) {
			return nextState == P2_REVEAL.toString();
		}
		return false;
	}

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_UPDATEANSWER:
			// Update answer
			if (data.length == 3 && data[1] instanceof Integer && data[2] instanceof String) {
				updateAnswer((Integer) data[1], (String) data[2]);
				return true;
			}
			else throw new InvalidDataException("Expecting a {*, Integer, String} when using action ACTION_UPDATEANSWER");

		case ACTION_UPDATESCORE:
			// Update answer
			if (data.length == 3 && data[1] instanceof Integer && data[2] instanceof Integer) {
				updateScore((Integer) data[1], (Integer) data[2]);
				return true;
			}
			else throw new InvalidDataException("Expecting a {*, Integer, Integer} when using action ACTION_UPDATESCORE");

		default: 
			throw new RuntimeException("Invalid action: " + action);
		}
	}
	
	/**
	 * Update the answer to a question by the player
	 * @param question
	 * @param answer
	 */
	private void updateAnswer(int question, String answer) {
		fastmoney.getAnswer(PLAYER, question).setAnswer(answer);
	}
	
	/**
	 * Update the score to a question by the player
	 * @param question
	 * @param score
	 */
	private void updateScore(int question, int score) {
		fastmoney.getAnswer(PLAYER, question).setScore(score);
	}
	
}
