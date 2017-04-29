package bwyap.gridgame.engine;

/**
 * An abstract class to hold the concept of a game engine.
 * Implementation of the game loop should be found in a sub-class 
 * which implements the {@code run()} method.
 * @author bwyap
 *
 */
public abstract class AbstractGameEngine implements GameEngineInterface {
	
	protected boolean running;
	
	protected final float fps;
	protected float measuredfps;
	
	/**
	 * Create a game engine with the desired target fps
	 * @param fps
	 */
	public AbstractGameEngine(float fps) {
		this.fps = fps;
		this.running = false;
	}
	
	@Override
	public void stop() {
		running = false;
	}
	
	@Override
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Get the value of the last measured fps.
	 * Warning: might not be the true value if it is not being measured by the implementing class.
	 * @return
	 */
	public float getMeasuredfps() {
		return measuredfps;
	}
	
}
