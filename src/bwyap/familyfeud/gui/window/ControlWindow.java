package bwyap.familyfeud.gui.window;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

/**
 * This is a control window with controls to 
 * run the Family Feud game from a separate window
 * @author bwyap
 *
 */
public class ControlWindow extends FamilyFeudWindow {
	
	private static final long serialVersionUID = -4445104890877967661L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	/**
	 * Create a new control window
	 * @param title
	 */
	public ControlWindow(String title) {
		super(title, WIDTH, HEIGHT);
	}

	/**
	 * Initialize window components
	 */
	public void initWindow() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setLayout(new GridBagLayout());
	}

}
