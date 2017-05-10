package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderingPanel;

/**
 * Renders the new game state:
 * each family's name will be rendered on the screen as they are added to the game.
 * @author bwyap
 *
 */
public class RenderEndGame extends AbstractRenderState {
	
	private FamilyFeudGame game;
	
	/**
	 * Create a new game render state
	 * @param game
	 */
	public RenderEndGame(FamilyFeudGame game) {
		this.game = game;
		
	}
	
	@Override
	public void update(float timeElapsed) {
		
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		
	}

}
