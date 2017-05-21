package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import bwyap.familyfeud.render.RenderingPanel;
import bwyap.utility.logging.Logger;

/**
 * Game window used to display the game graphics.
 * Supports full screen functionality, which by default displays on 2nd monitor.
 * @author bwyap
 *
 */
public class GameWindow extends FamilyFeudWindow {
	
	private static final long serialVersionUID = 5046186767241215679L;

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private boolean fullscreen;
	private int screenIndex = 1;
	
	private RenderingPanel renderSurface;
	
	/**
	 * Create a new game window
	 * @param title
	 */
	public GameWindow(String title, RenderingPanel renderSurface) {
		super(title, WIDTH, HEIGHT);
		this.renderSurface = renderSurface;
	}
	
	/**
	 * Initialize window components
	 */
	public void initWindow() {
		//setLocationRelativeTo(null);
		setFocusableWindowState(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		add(renderSurface, BorderLayout.CENTER);

		Logger.info("Game window initialized.");
	}
	
	/**
	 * Toggle the full screen state of this window
	 * @return true if the screen is now fullscreen, false otherwise
	 */
	public boolean toggleFullscreen() {
		dispose();
		if (fullscreen) {
			setSize(width, height);
		}
		else {
			GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
			if (screens.length == 1) {
				screens[0].setFullScreenWindow(this);
			}
			else if (screens.length > 1) {
				screens[screenIndex].setFullScreenWindow(this);
			}
		}
		fullscreen = !fullscreen;
		setVisible(true);
		return fullscreen;
	}
	
	/**
	 * Set the screen index the full screen game is displayed on
	 * @param screenIndex
	 */
	public void setDefaultFullscreenScreen(int screenIndex) {
		this.screenIndex = screenIndex;
	}
	
	/**
	 * Check if the window is currently in fullscreen mode
	 * @return
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	/**
	 * Get the rendering surface of the game window
	 * @return
	 */
	public RenderingPanel getRenderSurface() {
		return renderSurface;
	}

}
