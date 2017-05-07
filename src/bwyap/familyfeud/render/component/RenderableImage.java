package bwyap.familyfeud.render.component;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import bwyap.familyfeud.render.RenderingPanel;

/**
 * A container for an image that can be rendered to a RenderPanel.
 * @author bwyap
 *
 */
public class RenderableImage extends Renderable {
	
	private BufferedImage img;
	
	/**
	 * Create a renderable image
	 * @param img
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public RenderableImage(BufferedImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.img = img;
	}
	
	/**
	 * Create a renderable image at position (0, 0)
	 * @param img
	 * @param w
	 * @param h
	 */
	public RenderableImage(BufferedImage img, int w, int h) {
		this(img, 0, 0, w, h);
	}
	
	/**
	 * Create a renderable image at position (0, 0) that 
	 * will fill the rendering panel when rendered
	 * @param img
	 */
	public RenderableImage(BufferedImage img) {
		this(img, 0, 0, -1, -1);
	}

	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(img, panel.scaleX(x), panel.scaleY(y), w < 0 ? panel.getWidth() : panel.scaleX(w), h < 0 ? panel.getHeight() : panel.scaleY(h), null);
	}

}
