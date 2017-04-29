package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FFPlayStateMachine;
import bwyap.familyfeud.game.InvalidDataException;

/**
 * This state is used to control the game play
 * @author bwyap
 *
 */
public class StatePlay extends FFState {
	
	public static final int ACTION_EXECUTEPLAYACTION = 0x0;
	
	private FFPlayStateMachine stateMachine;
	
	protected StatePlay() {
		super(FFStateType.PLAY);
	}

	@Override
	public void initState() {
		stateMachine = new FFPlayStateMachine();
		stateMachine.init();
	}
	
	@Override
	public void cleanupState() { }

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_EXECUTEPLAYACTION:
			if (data[0] instanceof Integer) {
				stateMachine.getCurrentState().executeAction((Integer)data[0], data);
			}
			else throw new InvalidDataException("Expecting a {Integer, ...} when using action ACTION_EXECUTEPLAYACTION");
			break;
		}
		return false;
	}
	
}
