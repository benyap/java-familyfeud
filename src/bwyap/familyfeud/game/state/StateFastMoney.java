package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyState;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateMachine;
import bwyap.familyfeud.game.fastmoney.state.FastMoney;

/**
 * A state used to render fast money game play
 * @author bwyap
 *
 */
public class StateFastMoney extends FFState {
	
	private FFFastMoneyStateMachine stateMachine;
	
	private FastMoney fastmoney;
	
	protected StateFastMoney(FastMoney fastmoney) {
		super(FFStateType.FAST_MONEY);
		this.fastmoney = fastmoney;
	}
	
	@Override
	public void initState(Object data) {
		stateMachine = new FFFastMoneyStateMachine(fastmoney);
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
