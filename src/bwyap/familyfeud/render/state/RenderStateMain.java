package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the loading screen
 * @author bwyap
 *
 */
public class RenderStateMain implements RenderableInterface {

	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(ResourceLoader.getImage("title"), 0, 0, panel.getWidth(), panel.getHeight(), null);
	}

}
