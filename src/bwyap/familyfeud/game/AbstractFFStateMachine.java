package bwyap.familyfeud.game;

import bwyap.familyfeud.testdriver.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;
import bwyap.utility.statemachine.State;
import bwyap.utility.statemachine.StateMachine;

/**
 * Abstract StateMachine template for Family Feud.
 * @author bwyap
 *
 * @param <T>
 */
public class AbstractFFStateMachine<T extends State<?>> extends StateMachine<T> {
	
	public AbstractFFStateMachine(String string) {
		super(string);
	}

	@Override
	protected boolean getDebugMode() {
		// Return the appropriate debug variable
		return FamilyFeudTestDriver.DEBUG_LOG_CONSOLE;
	}
	
	@Override
	protected void printLog(String message) {
		Logger.info(message);
	}
	
	@Override
	protected void printTransition(String message) {
		Logger.warning(message);
	}
	
	@Override
	protected void printErr(String message) {
		Logger.err(message);
	}
	
}
