package bwyap.familyfeud.game;

import bwyap.familyfeud.game.state.FFState;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.statemachine.StateMachine;

/**
 * A state machine that handles {@code FFState} for Family Feud
 * @author bwyap
 *
 */
public class FFStateMachine extends StateMachine<FFState> {
	
	/**
	 * Create a Family Feud state machine
	 */
	public FFStateMachine() {
		
	}
	
	@Override
	public void init() {
		
		
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("FFStateMachine initialized.");
	}
	
}
