package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderPlayMachine;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the game screen background and the 
 * game state using a RenderPlayMachine object.
 * @author bwyap
 *
 */
public class RenderStatePlay extends AbstractRenderState {
	
	private FamilyFeudGame game;
	
	private Fader bg;
	private RenderPlayMachine machine;
	private RenderableInterface state;
	
	/**
	 * Create a new game play render state
	 * @param game
	 */
	public RenderStatePlay(FamilyFeudGame game) {
		this.game = game;
		this.machine = new RenderPlayMachine(this.game);
		
		bg = new Fader(1000, 
				new RenderableImage(ResourceLoader.getImage("bg")), 
				new RenderableImage(ResourceLoader.getImage("load")), false);
	}
	
	@Override
	public boolean canTransition() {
		if (machine.getState() != null) {
			return machine.getState().canTransition();
		}
		return true;
	}
	
	@Override
	public void update(float timeElapsed) {
		bg.update(timeElapsed);
		machine.update(timeElapsed);
		state = machine.getState();
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
		if (state!= null) state.render(panel, g);
	}

}
