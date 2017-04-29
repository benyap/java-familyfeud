package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.render.RenderStateInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the loading screen
 * @author bwyap
 *
 */
public class RenderStateLoading implements RenderStateInterface {

	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(ResourceLoader.getImage("load"), 0, 0, panel.getWidth(), panel.getHeight(), null);
	}

}
