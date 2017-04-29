package bwyap.familyfeud.gui.window;

import javax.swing.JFrame;

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
	
	/**
	 * Create a new game window
	 * @param title
	 */
	public GameWindow(String title) {
		super(title, WIDTH, HEIGHT);
		
	}
	
	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
