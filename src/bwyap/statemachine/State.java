package bwyap.statemachine;

/**
 * An abstract class that represents a template for a state in a state machine
 * @author bwyap
 *
 */
public abstract class State {
	
	private String name;
	private String nextState;
	
	public State(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * Initialize the state.
	 * This should be run before the state is updated for the first time.
	 */
	public abstract void initState();
	
	/**
	 * Update the state.
	 * @param timeElapsed
	 */
	public abstract void updateState(float timeElapsed);
	
	/**
	 * Clean up the state.
	 * This should be run before the state machine changes to another state.
	 */
	public abstract void cleanupState();
	
	/**
	 * The name of the next state to transition to.
	 * This should only be set when there should be a transition - 
	 * a {@code null} value indicates that no transition is necessary.
	 * @return
	 */
	public String nextState() {
		return nextState;
	}
	
	/**
	 * Set the next state to transition to.
	 * Note that when this is set to a value other than {@code null},
	 * the state machine will automatically attempt to transition to 
	 * the specified state on the next update.
	 * @param state
	 */
	protected void setNextState(String state) {
		this.nextState = state;
	}
	
}
