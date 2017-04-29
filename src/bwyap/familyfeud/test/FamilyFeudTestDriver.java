package bwyap.familyfeud.test;

import java.io.File;

import org.json.simple.JSONObject;

import bwyap.familyfeud.FamilyFeud;
import bwyap.familyfeud.FamilyFeudController;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateAddFamily;
import bwyap.familyfeud.game.state.StateLoadQuestions;
import bwyap.familyfeud.res.JSONQuestionSet;
import bwyap.utility.resource.JSONLoader;

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
		driver.testLoadQuestions();
		driver.testStartGame();
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
		app.getGame().changeState(FFStateType.NEW_GAME);
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
		app.getGame().changeState(FFStateType.ADD_FAMILY);
		
		// Add families
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Tran"});
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Lee"});
		
		// Finish adding families
		app.getGame().changeState(FFStateType.NEW_GAME);
		
		// Verify that families are added to the game
		System.out.println("# VERIFY > Families: " + app.getGame().getFamilies());
	}
	
	/**
	 * Test the loading of a question set from a JSON file
	 */
	public void testLoadQuestions() {
		File questionFile = new File("res/questionset.json");
		JSONObject o = JSONLoader.loadJSON(questionFile);
		JSONQuestionSet q = new JSONQuestionSet(o);
		
		app.getGame().changeState(FFStateType.LOAD_QUESTIONS);

		app.getGame().getState().executeAction(StateLoadQuestions.ACTION_LOADQUESTIONSET, new Object[]{q});
		
		// Finish adding questions
		app.getGame().changeState(FFStateType.NEW_GAME);

		// Verify that questions were added to the game
		System.out.println("# VERIFY > Questions: " + app.getGame().getQuestions());
	}
	
	/**
	 * Test starting the family feud game
	 */
	public void testStartGame() {
		app.getGame().changeState(FFStateType.INITIALIZE_GAME);
	}
	
}
