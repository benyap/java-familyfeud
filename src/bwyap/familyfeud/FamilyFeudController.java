package bwyap.familyfeud;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.gui.FFRenderingEngine;
import bwyap.familyfeud.gui.window.render.RenderingPanel;
import bwyap.familyfeud.test.FamilyFeudTestDriver;
import bwyap.utility.logging.Logger;

/**
 * Controller class that manages the game and GUI components of Family Feud
 * @author bwyap
 *
 */
public class FamilyFeudController {
	
	private static final int FPS_RATE = 60;
	
	private FamilyFeudGame game;
	private FamilyFeudGUI gui;
	private FFRenderingEngine engine;
	private Thread engineThread;
	
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
		
		RenderingPanel renderPanel = new RenderingPanel();
		
		gui = new FamilyFeudGUI(renderPanel);
		gui.init();
		
		engine = new FFRenderingEngine(FPS_RATE, renderPanel);
		engineThread = new Thread(engine);

		if (FamilyFeudTestDriver.DEBUG_LOG_CONSOLE) Logger.log("Controller initialized.");
	}
	
	/**
	 * Start the application
	 */
	public void start() {
		gui.start();
		engineThread.start();
	}
	
	/**
	 * Get the current Family Feud game
	 * @return
	 */
	public FamilyFeudGame getGame() {
		return game;
	}
	
}
