package bwyap.familyfeud.test;

import bwyap.familyfeud.FamilyFeud;
import bwyap.familyfeud.FamilyFeudController;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateAddFamily;

/**
 * Test Driver to test FamilyFeud application
 * @author bwyap
 *
 */
public class FamilyFeudTestDriver {
	
	public static final boolean DEBUG_LOG_CONSOLE = true;
	
	private FamilyFeudController app;

	/**
	 * Run the test driver
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("----------------------");
		System.out.println("> Running test driver ");
		System.out.println("----------------------");
		
		FamilyFeud.init();
		FamilyFeudTestDriver driver = new FamilyFeudTestDriver(FamilyFeud.app);
		
		// TESTS
		driver.testInit();
		driver.testStart();
		driver.testAddFamilies();
	}
	
	
	// ==================
	//    TEST METHODS   
	// ==================
	
	/**
	 * Create a new test driver with the given FamilyFeudController app
	 * @param app
	 */
	public FamilyFeudTestDriver(FamilyFeudController app) {
		this.app = app;
	}
	
	/**
	 * Test starting the application
	 */
	public void testStart() {
		app.start();
	}
	
	/**
	 * Test initializing the application
	 */
	public void testInit() {
		app.init();
	}
	
	/**
	 * Test adding families to the game
	 */
	public void testAddFamilies() {
		app.getGame().changeState(FFStateType.NEW_GAME);
		app.getGame().changeState(FFStateType.ADD_FAMILY);
		
		// Add families
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Tran"});
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Lee"});
		
		// Finish adding families
		app.getGame().changeState(FFStateType.NEW_GAME);
		
		// Verify that families are added to the game
		System.out.println("Families: " + app.getGame().getFamilies());
	}
	
}
