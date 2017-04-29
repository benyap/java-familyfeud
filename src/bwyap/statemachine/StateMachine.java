package bwyap.statemachine;

import java.util.HashMap;

import bwyap.utility.logging.Logger;

/**
 * A basic state machine which provide the {@code update()} method for each state.
 * @author bwyap
 *
 * @param <T> A subclass of {@code State} 
 */
public class StateMachine<T extends State> {
	
	private final String name;
	protected T currentState;
	protected HashMap<String, T> states = new HashMap<String, T>();
	
	/**
	 * Create a new state machine with the specified name
	 * @param name
	 */
	public StateMachine(String name) {
		this.name = name;
	}
	
	/**
	 * Create a new state machine with an empty name
	 */
	public StateMachine() {
		this("null");
	}
	
	/**
	 * Get the name of this state machine
	 * @return
	 */
	public String getName() {
		return name;
	}
	
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
	public boolean validateStateTransition(T currentState, String nextState) {
		return true;
	}
	
	/**
	 * Add a state to the state machine
	 * @param name
	 * @param state
	 */
	public void addState(String name, T state) {
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
	 * Get the current state
	 * @return
	 */
	public T getCurrentState() {
		return currentState;
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
			if (currentState != null && !currentState.canAdvance()) {
				if (getDebugMode()) printErr("> Invalid state transition: <" + currentState + "> is not complete and cannot advance to <" + name + ">");
			}
			else {
				if (currentState != null) currentState.cleanupState();
				if (states.containsKey(name)) {
					if (getDebugMode()) printLog(getName() + " > CHANGING STATE from <" + currentState + "> to <" + name + ">");
					Object data = null;
					if (currentState != null) data = currentState.getData();
					currentState = states.get(name);
					currentState.initState(data);
				}
				else {
					// no state 
					currentState = null;
				}
			}
		}
		else {
			if (getDebugMode()) printErr(getName() + " > Invalid state transition: <" + currentState + "> to <" + name + ">");
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
	
	/**
	 * Get the debug mode for the state machine. 
	 * By default, this is {@code true} so all debug information will be printed. 
	 * @return
	 */
	protected boolean getDebugMode() {
		return true;
	}
	
	/**
	 * Log a message to the console.
	 * By default this logs the message to {@code System.out}. 
	 * Override this method to direct the message.
	 * @param message
	 */
	protected void printLog(String message) {
		Logger.log(message);
	}
	
	/**
	 * Log am error message to the console.
	 * By default this logs the message to {@code System.err}. 
	 * Override this method to direct the message.
	 * @param message
	 */
	protected void printErr(String message) {
		Logger.err(message);
	}
	
}
