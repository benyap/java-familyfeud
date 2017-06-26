package bwyap.familyfeud.game.fastmoney.state;

import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_ANSWER;

import bwyap.familyfeud.game.InvalidDataException;

/**
 * This state represents when a player's answers are being revealed in Fast Money.
 * @author bwyap
 *
 */
public class StateReveal extends FFFastMoneyState {

	public static final int ACTION_REVEALANSWER = 0x12;

	private final int PLAYER;
	
	public StateReveal(FastMoney fastmoney, int player) {
		super(fastmoney, player);
		this.PLAYER = player;
	}
	
	@Override
	public void initState(Object data) { }

	@Override
	public void cleanupState() { }
	
	@Override
	public boolean canAdvance(String nextState) {
		if (PLAYER == 0) {
			return nextState == P2_ANSWER.toString();
		}
		return false;
	}

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_REVEALANSWER:
			// Reveal an answer
			if (data.length == 3 && data[1] instanceof Integer && data[2] instanceof Boolean) {
				revealAnswer((Integer) data[1], (Boolean) data[2]);
				return true;
			}
			else throw new InvalidDataException("Expecting a {*, Integer, Boolean} when using action ACTION_UPDATEANSWER");

		default: 
			throw new RuntimeException("Invalid action: " + action);
		}
	}
	
	/**
	 * Reveal a player's answer
	 */
	private void revealAnswer(int question, boolean revealed) {
		fastmoney.setRevealedAnswer(PLAYER, question, revealed);
	}

}
