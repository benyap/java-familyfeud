package bwyap.familyfeud.game.fastmoney.state;

import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P1_ANSWER;
import static bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateType.P2_ANSWER;

import bwyap.utility.statemachine.State;

/**
 * Represents a state in the FAST_MONEY state of a game of Family Feud.
 * Inherit this class to create a specific game sate.
 * @author bwyap
 *
 */
public abstract class FFFastMoneyState extends State<FFFastMoneyStateType> {
	
	protected FastMoney fastmoney;
	
	/**
	 * Create a new state with the specified name
	 * @param name
	 */
	protected FFFastMoneyState(FastMoney fastmoney, int player) {
		super(player == 0 ? P1_ANSWER : (player == 1 ? P2_ANSWER : null));
		this.fastmoney = fastmoney;
	}
	
	@Override
	public void updateState(float timeElapsed) {
		// This method is not needed for this state machine
	}
	
	/**
	 * Get the FastMoney instance from this state
	 * @return
	 */
	public FastMoney getFastMoney() {
		return fastmoney;
	}
}
