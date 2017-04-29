package bwyap.familyfeud;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * Controller class that manages the game and GUI components of Family Feud
 * @author bwyap
 *
 */
public class FamilyFeudController {
	
	private FamilyFeudGame game;
	private FamilyFeudGUI gui;
	
	/**
	 * Create a new Family Feud controller
	 */
	public FamilyFeudController() {
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.log("Welcome to Family Feud!");
	}
	
	/**
	 * Initialize the application
	 */
	public void init() {
		game = new FamilyFeudGame();		
		game.init();
		
		gui = new FamilyFeudGUI();
		gui.init();

		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.log("Controller initialized.");
	}
	
	/**
	 * Start the application
	 */
	public void start() {
		gui.start();
	}
	
	/**
	 * Get the current Family Feud game
	 * @return
	 */
	public FamilyFeudGame getGame() {
		return game;
	}
	
}
