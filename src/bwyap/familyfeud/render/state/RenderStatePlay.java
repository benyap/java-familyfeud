package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the game screen
 * @author bwyap
 *
 */
public class RenderStatePlay implements RenderableInterface {
	
	private FamilyFeudGame game;
	
	private Fader bg;

	/**
	 * Create a new game play render state
	 * @param game
	 */
	public RenderStatePlay(FamilyFeudGame game) {
		this.game = game;
		bg = new Fader(1000, 
				new RenderableImage(ResourceLoader.getImage("bg")), 
				new RenderableImage(ResourceLoader.getImage("load")), false);
	}
	
	@Override
	public void update(float timeElapsed) {
		bg.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
	}

}
