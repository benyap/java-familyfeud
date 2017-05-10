package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.utility.logging.Logger;

/**
 * A state used to render the final winner
 * @author bwyap
 *
 */
public class StateEndGame extends FFState {

	private FamilyFeudGame game;
	
	
	protected StateEndGame(FamilyFeudGame game) {
		super(FFStateType.END_GAME);
		this.game = game;
	}

	@Override
	public void initState(Object data) {
		// Set the winner
		game.setWinner();
		
		// Log the winner
		Logger.info("Congratulations to <" + game.getWinningFamily().getName() + "> for winning with " + game.getWinningFamily().getPoints() + " points!");
	}

	@Override
	public void updateState(float timeElapsed) { }

	@Override
	public void cleanupState() { }

}