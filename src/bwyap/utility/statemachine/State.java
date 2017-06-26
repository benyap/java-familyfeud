package bwyap.utility.statemachine;

/**
 * An abstract class that represents a template for a state in a state machine
 * @author bwyap
 *
 */
public abstract class State<T> {
	
	private String name;
	private String nextState;
	protected Object data;
	private T type;

	public State(T type) {
		this.name = type.toString();
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	/**
	 * Get the state type.
	 * @return
	 */
	public T getType() {
		return type;
	}

	/**
	 * Execute an action for that state.
	 * Each state should specify a set of valid actions and appropriate action handlers.
	 * If the specified action could not be executed, the method will return false.
	 * @param action
	 * @param data
	 * @return true if the action was successfully executed.
	 */
	public boolean executeAction(int action, Object[] data) {
		return false;
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
