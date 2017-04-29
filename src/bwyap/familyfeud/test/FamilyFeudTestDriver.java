package bwyap.familyfeud.test;

import java.io.File;

import org.json.simple.JSONObject;

import bwyap.familyfeud.FamilyFeud;
import bwyap.familyfeud.FamilyFeudController;
import bwyap.familyfeud.game.play.state.StateSelectQuestion;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateAddFamily;
import bwyap.familyfeud.game.state.StateLoadQuestions;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.res.JSONQuestionSet;
import bwyap.utility.resource.JSONLoader;

/**
 * Test Driver to test FamilyFeud application
 * @author bwyap
 *
 */
public class FamilyFeudTestDriver {
	
	public static final boolean DEBUG_LOG_CONSOLE = true;
	public static final boolean LOG_VERIFY = true;
	
	private FamilyFeudController app;

	/**
	 * Run the test driver
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-----------------------");
		System.out.println("! Running test driver !");
		System.out.println("-----------------------");
		
		FamilyFeud.init();
		FamilyFeudTestDriver driver = new FamilyFeudTestDriver(FamilyFeud.app);
		
		// TESTS
		driver.testInit();
		driver.testStart();
		driver.testAddFamilies();
		driver.testLoadQuestions();
		driver.testInitGame();
		driver.testPlayGame();
		driver.testSelectQuestion();
		driver.testFaceOff();
	}
	
	
	// ==================
	//    TEST METHODS   
	// ==================
	
	/**
	 * Create a new test driver with the given FamilyFeudController app
	 * @param app
	 */
	protected FamilyFeudTestDriver(FamilyFeudController app) {
		this.app = app;
	}
	
	/**
	 * Test starting the application
	 */
	protected void testStart() {
		app.start();
		app.getGame().changeState(FFStateType.NEW_GAME);
	}
	
	/**
	 * Test initializing the application
	 */
	protected void testInit() {
		app.init();
	}
	
	/**
	 * Test adding families to the game
	 */
	protected void testAddFamilies() {
		app.getGame().changeState(FFStateType.ADD_FAMILY);
		
		// Add families
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Tran"});
		app.getGame().getState().executeAction(StateAddFamily.ACTION_ADDFAMILY, new Object[]{"Lee"});
		
		// Finish adding families
		app.getGame().changeState(FFStateType.NEW_GAME);
		
		// Verify that families are added to the game
		if (LOG_VERIFY) System.out.println("### VERIFY > Families: " + app.getGame().getFamilies());
	}
	
	/**
	 * Test the loading of a question set from a JSON file
	 */
	protected void testLoadQuestions() {
		File questionFile = new File("res/questionset.json");
		JSONObject o = JSONLoader.loadJSON(questionFile);
		JSONQuestionSet q = new JSONQuestionSet(o);
		
		app.getGame().changeState(FFStateType.LOAD_QUESTIONS);

		app.getGame().getState().executeAction(StateLoadQuestions.ACTION_LOADQUESTIONSET, new Object[]{q});
		
		// Finish adding questions
		app.getGame().changeState(FFStateType.NEW_GAME);

		// Verify that questions were added to the game
		if (LOG_VERIFY) System.out.println("### VERIFY > Questions: " + app.getGame().getQuestions());
	}
	
	/**
	 * Test initialize the game
	 */
	protected void testInitGame() {
		app.getGame().changeState(FFStateType.INITIALIZE_GAME);
	}
	
	/**
	 * Test starting the family feud game
	 */
	protected void testPlayGame() {
		app.getGame().changeState(FFStateType.PLAY);
	}
	
	/**
	 * Test the selection of question in the PLAY state
	 */
	protected void testSelectQuestion() {
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateSelectQuestion.ACTION_SELECTQUESTION, 2
		});
	}
	
	/**
	 * Test face off
	 */
	protected void testFaceOff() {
		app.getGame().getState().executeAction(StatePlay.CHANGESTATE_FACEOFF, null);
	}
	
}
