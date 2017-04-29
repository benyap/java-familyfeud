package bwyap.familyfeud;

import static bwyap.familyfeud.FamilyFeud.TITLE;
import static bwyap.familyfeud.FamilyFeud.VERSION;

import bwyap.familyfeud.gui.window.ControlWindow;
import bwyap.familyfeud.gui.window.GameWindow;
import bwyap.familyfeud.gui.window.render.RenderingPanel;

/**
 * This class is responsible for handling all GUI components of the Family Feud application
 * @author bwyap
 *
 */
public class FamilyFeudGUI {
	
	private GameWindow gameWindow;
	private ControlWindow controlWindow;
	
	public FamilyFeudGUI(RenderingPanel renderPanel) {
		gameWindow = new GameWindow(TITLE, renderPanel);
		controlWindow = new ControlWindow(TITLE + " controller " + VERSION);
	}
	
	/**
	 * Initialize GUI components
	 */
	public void init() {
		gameWindow.initWindow();
		controlWindow.initWindow();
	}
	
	/**
	 * Start the application by showing the windows
	 */
	public void start() {
		controlWindow.setVisible(true);
		gameWindow.setVisible(true);
	}
	
}
