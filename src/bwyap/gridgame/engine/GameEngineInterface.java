package bwyap.gridgame.engine;

/**
 * A game engine must provide these methods.
 * @author bwyap
 *
 */
public interface GameEngineInterface extends Runnable {
	
	/**
	 * Start the game loop
	 */
	public void run();
	
	/**
	 * Stop the game loop
	 */
	public void stop();
	
	/**
	 * Check if the game loop is running
	 * @return
	 */
	public boolean isRunning();
	
	/**
	 * Update the game state
	 * @param timeElapsed
	 */
	public void update(float timeElapsed);
	

	/**
	 * Render the game state
	 * @param timeElapsed
	 */
	public void render();
	
}
