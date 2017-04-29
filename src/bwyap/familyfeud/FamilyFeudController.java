package bwyap.familyfeud;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.test.FamilyFeudTestDriver;

/**
 * Controller class that manages the game and GUI components of Family Feud
 * @author bwyap
 *
 */
public class FamilyFeudController {
	
	private FamilyFeudGame game;
	
	/**
	 * Create a new Family Feud controller
	 */
	public FamilyFeudController() {
		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("Welcome to Family Feud!");
	}
	
	/**
	 * Initialize the application
	 */
	public void init() {
		game = new FamilyFeudGame();		
		game.init();

		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) System.out.println("Controller initialized.");
	}
	
	/**
	 * Start the application
	 */
	public void start() {
		
	}
	
	/**
	 * Get the current Family Feud game
	 * @return
	 */
	public FamilyFeudGame getGame() {
		return game;
	}
	
}
