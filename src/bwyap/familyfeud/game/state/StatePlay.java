package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FFPlayStateMachine;

/**
 * This state is used to control the game play
 * @author bwyap
 *
 */
public class StatePlay extends FFState {
	
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

	
}
