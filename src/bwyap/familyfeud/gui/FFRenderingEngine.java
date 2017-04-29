package bwyap.familyfeud.gui;

import bwyap.familyfeud.gui.window.render.RenderingPanel;
import bwyap.gridgame.engine.FixedTimeStepGameEngine;

/**
 * A custom implementation of a game engine to render 
 * graphics to the game window for Family Feud
 * @author bwyap
 *
 */
public class FFRenderingEngine extends FixedTimeStepGameEngine {

	private RenderingPanel renderSurface;
	
	/**
	 * Create a new Family Feud rendering engine
	 * at the specified fps rate
	 * @param fps
	 */
	public FFRenderingEngine(float fps, RenderingPanel renderSurface) {
		super(fps);
		this.renderSurface = renderSurface;
	}
	
	@Override
	public void render() {
		renderSurface.render();
	}
	
	@Override
	public void update(float timeElapsed) {
		renderSurface.updateFPS(getMeasuredfps());
	}
	
}
