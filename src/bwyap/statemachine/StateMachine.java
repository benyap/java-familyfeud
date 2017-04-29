package bwyap.statemachine;

import java.util.HashMap;

/**
 * A basic state machine which provide the {@code update()} method for each state.
 * @author bwyap
 *
 * @param <T> A subclass of {@code State} 
 */
public class StateMachine<T extends State> {
	
	protected State currentState;
	protected HashMap<String, State> states = new HashMap<String, State>();
	
	/**
	 * Initialize the state machine's default states.
	 */
	public void init() {
		
	}
	
	/**
	 * Validate a state transition.
	 * By default, this returns true.
	 * Override this method to implement custom transition validation.
	 * @param currentState
	 * @param nextState
	 * @return
	 */
	public boolean validateStateTransition(State currentState, String nextState) {
		return true;
	}
	
	/**
	 * Add a state to the state machine
	 * @param name
	 * @param state
	 */
	public void addState(String name, State state) {
		states.put(name, state);
	}
	
	/**
	 * Get the name of the current state
	 * @return
	 */
	public String getCurrentStateName() {
		if (currentState != null) {
			return currentState.getName();
		}
		else return "null";
	}
	
	/**
	 * Remove a state from the state machine
	 * @param name
	 */
	public void removeState(String name) {
		states.remove(name);
	}
	
	/**
	 * Change the current state to the one specified by the name.
	 * If no such state exists, it is set to null.
	 * @param name
	 */
	public void changeState(String name) {
		if (validateStateTransition(currentState, name)) {
			if (currentState != null) currentState.cleanupState();
			if (states.containsKey(name)) {
				currentState = states.get(name);
				currentState.initState();
				System.out.println("CHANGING STATE: " + name);
			}
			else {
				// no state 
				currentState = null;
			}
		}
		else {
			System.err.println("Invalid state transition: <" + currentState.getName() + "> to <" + name + ">");
		}
	}
	
	/**
	 * Update the current state.
	 * @param timeElapsed 
	 */
	public void update(float timeElapsed) {
		if(currentState != null) {
			currentState.updateState(timeElapsed);
			if(currentState.nextState() != null) {
				changeState(currentState.nextState());
			}
		}
	}
	
}
