package bwyap.familyfeud.gui.window;

import javax.swing.JFrame;

/**
 * A basic window class for Family Feud
 * @author bwyap
 *
 */
public abstract class FamilyFeudWindow extends JFrame {
	
	private static final long serialVersionUID = 8618638638507149768L;
	
	protected int width, height;
	
	/**
	 * Create a new window with the given title and dimensions
	 * @param title
	 */
	public FamilyFeudWindow(String title, int width, int height) {
		super(title);
		this.width = width;
		this.height = height;
		setSize(width, height);
	}
	
}
