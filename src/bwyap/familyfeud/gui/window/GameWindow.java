package bwyap.familyfeud.gui.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import bwyap.familyfeud.gui.window.render.RenderingPanel;

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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(renderSurface, BorderLayout.CENTER);
	}

}
