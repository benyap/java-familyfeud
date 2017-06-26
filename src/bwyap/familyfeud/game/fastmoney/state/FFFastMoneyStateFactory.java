package bwyap.familyfeud.game.fastmoney.state;

/**
 * This class is responsible for creating and initializing the game states within the Family Feud fast money phase.
 * Each state should only be created once.
 * @author bwyap
 *
 */
public class FFFastMoneyStateFactory {
	
	private static FFFastMoneyState p1Answer;
	private static FFFastMoneyState p1Reveal;
	private static FFFastMoneyState p2Answer;
	private static FFFastMoneyState p2Reveal;

	/**
	 * Get the FFPlayState object for the specified state.
	 * A new state object is created if it has not already been created.
	 * @param type
	 * @return
	 */
	public static FFFastMoneyState getState(FFFastMoneyStateType type, Object data) {
		switch(type) {
		case P1_ANSWER:
			if (p1Answer == null) createP1Answer((FastMoney) data);
			return p1Answer;
		case P1_REVEAL:
			if (p1Reveal == null) createP1Reveal((FastMoney) data);
			return p1Reveal;
		case P2_ANSWER:
			if (p2Answer == null) createP2Answer((FastMoney) data);
			return p2Answer;
		case P2_REVEAL:
			if (p2Reveal == null) createP2Reveal((FastMoney) data);
			return p2Reveal;		
		}
		return null;
	}
	
	// =================
	// Create the states
	// =================
	
	private static void createP1Answer(FastMoney fastmoney) {
		p1Answer = new StateAnswer(fastmoney, 0);
	}
	
	private static void createP1Reveal(FastMoney fastmoney) {
		p1Reveal = new StateReveal(fastmoney, 0);
	}
	
	private static void createP2Answer(FastMoney fastmoney) {
		p2Answer = new StateAnswer(fastmoney, 1);
	}
	
	private static void createP2Reveal(FastMoney fastmoney) {
		p2Reveal = new StateReveal(fastmoney, 1);
	}
	
}
