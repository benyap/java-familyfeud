package bwyap.familyfeud.game.state;

/**
 * This state is used to initialize any necessary game objects.
 * @author bwyap
 *
 */
public class StateInitializeGame extends FFState {

	protected StateInitializeGame() {
		super(FFStateType.INITIALIZE_GAME);
	}

	@Override
	public void initState(Object data) { }

	@Override
	public void cleanupState() { }

}
