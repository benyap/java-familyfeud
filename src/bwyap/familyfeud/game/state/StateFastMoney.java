package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyState;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateMachine;

/**
 * A state used to render fast money game play
 * @author bwyap
 *
 */
public class StateFastMoney extends FFState {
	
	private FFFastMoneyStateMachine stateMachine;
	
	protected StateFastMoney() {
		super(FFStateType.FAST_MONEY);
	}
	
	@Override
	public void initState(Object data) {
		stateMachine = new FFFastMoneyStateMachine();
		stateMachine.init();
	}
	
	@Override
	public void cleanupState() { }
	
	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		
		}
		return false;
	}
	
	/**
	 * Get the current fast money state
	 * @return
	 */
	public FFFastMoneyState getPlayState() {
		if (stateMachine!= null) return stateMachine.getCurrentState();
		return null;
	}
}
