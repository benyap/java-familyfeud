package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyState;
import bwyap.familyfeud.game.fastmoney.state.FFFastMoneyStateMachine;
import bwyap.familyfeud.game.fastmoney.state.FastMoney;

/**
 * A state used to render fast money game play
 * @author bwyap
 *
 */
public class StateFastMoney extends FFState {

	public static final int ACTION_EXECUTEFASTMONEYACTION = 0x00;

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
		case ACTION_EXECUTEFASTMONEYACTION:
			if (data[0] instanceof Integer) {
				return stateMachine.getCurrentState().executeAction((Integer)data[0], data);
			}
			else throw new InvalidDataException("Expecting a {Integer, ...} when using action [" + action + "]");
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
