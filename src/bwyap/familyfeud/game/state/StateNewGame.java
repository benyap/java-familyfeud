package bwyap.familyfeud.game.state;

/**
 * This state represents the waiting state while the
 * question set is loaded and families are added before start a new game.
 * @author bwyap
 *
 */
public class StateNewGame extends FFState {
	
	protected StateNewGame() {
		super(FFStateType.NEW_GAME);
	}

	@Override
	public void initState() { }

	@Override
	public void cleanupState() { }

}
