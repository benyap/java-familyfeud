package bwyap.gridgame.engine;

/**
 * A fixed time step game engine which implements the {@code run()} 
 * method from the {@code Runnable} interface.
 * Can be run in its own thread.
 * @author bwyap
 *
 */
public abstract class FixedTimeStepGameEngine extends AbstractGameEngine {
	
	private final float MS_PER_SEC = 1000;
	private final float MS_PER_TICK;
	
	/**
	 * Create a fixed time step engine with the desired target fps
	 * @param fps
	 */
	public FixedTimeStepGameEngine(float fps) {
		super(fps);
		MS_PER_TICK = 1000.0f/fps;
	}
	
	@Override
	public void run() {
		running = true;
		
		long now = System.currentTimeMillis();
		long last;
		long elapsed = 0;
		long fpsTick = 0;
		int fpsCounter = 0;
		
		while(running) {
			last = now;
			now = System.currentTimeMillis();
			
			elapsed += now - last;
			fpsTick += now - last;
			
			if (elapsed >= MS_PER_TICK) {

				// update and render the game
				update(elapsed);
				render();
				
				elapsed -= MS_PER_TICK;
				fpsCounter++;
			}
			
			if (fpsTick >= MS_PER_SEC) {
				// update the fps
				this.measuredfps = fpsCounter;
				fpsTick -= MS_PER_SEC;
				fpsCounter = 0;
			}
		}
	}

}
