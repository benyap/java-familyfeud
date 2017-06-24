package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.utility.logging.Logger;

/**
 * This state creates a new game and allows a new
 * question set to be loaded and new families to be added. 
 * @author bwyap
 *
 */
public class StateNewGame extends FFState {
	
	private FamilyFeudGame game;
	
	protected StateNewGame(FamilyFeudGame game) {
		super(FFStateType.NEW_GAME);
		this.game = game;
	}

	@Override
	public void initState(Object data) {
		game.reset();
		Logger.info("New game created.");
	}

	@Override
	public void cleanupState() { }

}
