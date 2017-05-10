package bwyap.familyfeud.render;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.gridgame.engine.FixedTimeStepGameEngine;
import bwyap.utility.logging.Logger;

/**
 * A custom implementation of a game engine to render 
 * graphics to the game window for Family Feud
 * @author bwyap
 *
 */
public class FFRenderingEngine extends FixedTimeStepGameEngine {

	private RenderingPanel renderSurface;	
	private RenderMachine machine;

	/**
	 * Create a new Family Feud rendering engine
	 * at the specified fps rate
	 * @param fps
	 */
	public FFRenderingEngine(int fpsRate, RenderingPanel renderPanel, FamilyFeudGame game) {
		super(fpsRate);
		this.renderSurface = renderPanel;
		this.machine = new RenderMachine(game);
	}

	@Override
	public void render() {
		renderSurface.render(machine.getState());
	}
	
	@Override
	public void update(float timeElapsed) {
		try {
			machine.update(timeElapsed);
			renderSurface.updateFPS(getMeasuredfps());			
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger.err("Unexpected RuntimeError occured: " + e.getMessage());
		}
	}
	
}
