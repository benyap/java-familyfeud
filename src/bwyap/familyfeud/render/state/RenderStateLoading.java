package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the loading screen
 * @author bwyap
 *
 */
public class RenderStateLoading extends AbstractRenderState {

	private Fader bg;
	
	public RenderStateLoading() {
		bg = new Fader(300, 
				new RenderableImage(ResourceLoader.getImage("load")), 
				new RenderableImage(ResourceLoader.getImage("blur")), false);
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
