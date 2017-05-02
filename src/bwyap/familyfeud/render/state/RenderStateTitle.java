package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the title screen
 * @author bwyap
 *
 */
public class RenderStateTitle extends AbstractRenderState {

	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(ResourceLoader.getImage("title"), 0, 0, panel.getWidth(), panel.getHeight(), null);
	}

}
