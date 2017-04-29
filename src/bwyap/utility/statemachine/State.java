package bwyap.utility.statemachine;

/**
 * An abstract class that represents a template for a state in a state machine
 * @author bwyap
 *
 */
public abstract class State {
	
	private String name;
	private String nextState;
	protected Object data;
	
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
	 * @param data
	 */
	public abstract void initState(Object data);
	
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
	 * Check whether this state is ready to advance the given state.
	 * By default, this is {@code true}. 
	 * Override this method to implement custom advance control. 
	 * @param nextState 
	 */
	public boolean canAdvance(String nextState) {
		return true;
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
	
	/**
	 * Get the return data for this state.
	 * The return object is used to get data from this state at the end of its life
	 * to be used by the state machine to transfer information to the next state.
	 * @return
	 */
	public Object getData() {
		return data;
	}
	
}
