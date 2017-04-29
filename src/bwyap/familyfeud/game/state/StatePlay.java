package bwyap.familyfeud.game.state;

/**
 * This state is used to control the game play
 * @author bwyap
 *
 */
public class StatePlay extends FFState {

	protected StatePlay() {
		super(FFStateType.PLAY);
	}

	@Override
	public void initState() { }
	
	@Override
	public void cleanupState() { }

	
}
