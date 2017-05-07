package bwyap.familyfeud.render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import bwyap.familyfeud.gui.window.GameWindow;

/**
 * A panel used to render game graphics using Java's Graphics API.
 * @author bwyap
 *
 */
public class RenderingPanel extends JPanel {

	private static final long serialVersionUID = 8000180076902777265L;
	
	public static final int DEFAULT_WIDTH = GameWindow.WIDTH;
	public static final int DEFAULT_HEIGHT = GameWindow.HEIGHT;
	
	private boolean renderFPS = true;
	private float lastFPS;
	
	private AbstractRenderState state;
	
	/**
	 * Get the scaled x value according to the size of the panel,
	 * using the original intended GameWindow width as its reference
	 * @param x
	 * @return
	 */
	public int scaleX(int x) {
		return (int)((float)this.getWidth()/(float)DEFAULT_WIDTH * x);
	}
	
	/**
	 * Get the scaled y value according to the size of the panel,
	 * using the original intended GameWindow height as its reference
	 * @param y
	 * @return
	 */
	public int scaleY(int y) {
		return (int)((float)this.getHeight()/(float)DEFAULT_HEIGHT * y);
	}
	
	/**
	 * Render graphics
	 */
	public void render(AbstractRenderState state) {
		this.state = state;
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		setAntiAlias((Graphics2D)g);
		
		// Render the game state to the screen
		if (state != null) state.render(this, g);
		
		// Draw fps to the screen
		if (renderFPS) {
			// Reset any alpha settings
			((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g.setColor(Color.YELLOW);
			g.setFont(new Font("SansSerif", Font.PLAIN, 12));
			g.drawString((int) lastFPS + " fps", 2, 12);			
		}
	}

	/**
	 * Set anti-aliasing for rendering
	 * @param g
	 */
	private void setAntiAlias(Graphics2D g) {
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
	
	/**
	 * Set whether the FPS is rendered to the screen
	 * @param show
	 */
	public void showFPS(boolean show) {
		renderFPS = show;
	}
	
	/** 
	 * Check if the render panel is currently showing the fps
	 * @return
	 */
	public boolean showingFPS() {
		return renderFPS;
	}
	
	/**
	 * Set the value of the last measured fps to render to the screen
	 * @param fps
	 */
	public void updateFPS(float fps) {
		lastFPS = fps;
	}
	
}
