package bwyap.familyfeud.test;

import java.io.File;

import org.json.simple.JSONObject;

import bwyap.familyfeud.FamilyFeud;
import bwyap.familyfeud.FamilyFeudController;
import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.play.state.StateFaceOff;
import bwyap.familyfeud.game.play.state.StateFamilyPlay;
import bwyap.familyfeud.game.play.state.StateFamilySteal;
import bwyap.familyfeud.game.play.state.StateSelectQuestion;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StateAddFamily;
import bwyap.familyfeud.game.state.StateLoadQuestions;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.res.JSONQuestionSet;
import bwyap.utility.logging.Logger;
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
		Logger.print("-----------------------");
		Logger.print("! Running test driver !");
		Logger.print("-----------------------");
		
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
		driver.testFamilyPlay();
		driver.testFamilySteal();
		driver.testAllocatePoints();
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
		if (LOG_VERIFY) Logger.info("### VERIFY > Families: " + app.getGame().getFamilies());
	}
	
	/**
	 * Test the loading of a question set from a JSON file
	 */
	protected void testLoadQuestions() {
		File questionFile = new File("res/questionset.json");
		JSONObject o = JSONLoader.loadJSON(questionFile);
		JSONQuestionSet q = new JSONQuestionSet(o);
		q.isValid();
		
		app.getGame().changeState(FFStateType.LOAD_QUESTIONS);

		app.getGame().getState().executeAction(StateLoadQuestions.ACTION_LOADQUESTIONSET, new Object[]{q});
		
		// Finish adding questions
		app.getGame().changeState(FFStateType.NEW_GAME);

		// Verify that questions were added to the game
		if (LOG_VERIFY) Logger.info("### VERIFY > Questions: ");
		if (LOG_VERIFY) {
			String s = "";
			for(Question question : app.getGame().getQuestions()) {
				s = (question.toString()) + ": [ ";
				for(Answer a : question.getAnswers()) {
					s += a + "(" + a.getValue() + ") ";
				}
				s += "]";
			}
			Logger.info(s);
		}
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
				StateSelectQuestion.ACTION_SELECTQUESTION, 1
		});
	}
	
	/**
	 * Test face off
	 */
	protected void testFaceOff() {
		app.getGame().getState().executeAction(StatePlay.CHANGESTATE_FACEOFF, null);
		
		// Reveal and answer
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFaceOff.ACTION_OPENANSWER, 3
		});
		if (LOG_VERIFY) {
			for(Answer a : app.getGame().getQuestions().get(1).getAnswers()) {
				Logger.info(a + ": " + a.isRevealed());
			}
		}
		
		// choose family
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFaceOff.ACTION_CHOOSEFAMILY, 0
		});

	}
	
	/**
	 * Test family play
	 */
	protected void testFamilyPlay() {
		app.getGame().getState().executeAction(StatePlay.CHANGESTATE_FAMILYPLAY, null);
		
		// Reveal an answer
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_OPENANSWER, 1
		});
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_OPENANSWER, 0
		});
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_OPENANSWER, 2
		});
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_OPENANSWER, 3
		});
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_OPENANSWER, 4
		});
	
		if (LOG_VERIFY) {
			for(Answer a : app.getGame().getQuestions().get(1).getAnswers()) {
				Logger.info(a + ": " + a.isRevealed());
			}
		}
		
		// Give three strikes to the family
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_STRIKE
		});
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_STRIKE
		});	
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilyPlay.ACTION_STRIKE
		});	
		
		// Try to reveal an answer
		//app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
		//		StateFamilyPlay.ACTION_OPENANSWER, 0
		//});
	}
	
	/**
	 * Test family steal
	 */
	protected void testFamilySteal() {
		app.getGame().getState().executeAction(StatePlay.CHANGESTATE_FAMILYSTEAL, null);
		
		// Choose steal family
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilySteal.ACTION_SELECTSTEALFAMILY, 1
		});	
		
		// Apply strike
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilySteal.ACTION_STRIKE
		});	
		
		// Select winning family
		app.getGame().getState().executeAction(StatePlay.ACTION_EXECUTEPLAYACTION, new Object[]{
				StateFamilySteal.ACTION_SELECTWINFAMILY, 0
		});	
	}
	
	/**
	 * Test allocating points to the selected family
	 */
	protected void testAllocatePoints() {
		app.getGame().getState().executeAction(StatePlay.CHANGESTATE_ALLOCATEPOINTS, null);
		
		// Verify that points were added to the family
		if (LOG_VERIFY) {
			if (LOG_VERIFY) Logger.info("### VERIFY > Family status: ");
			for(Family f : app.getGame().getFamilies()) {
				Logger.info(f + " family: " + f.getPoints() + " points");
			}
		}
	}
	
}
