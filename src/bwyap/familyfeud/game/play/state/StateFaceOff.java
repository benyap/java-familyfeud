package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state handles when the two families are facing off 
 * to see which one gets to choose to play
 * @author bwyap
 *
 */
public class StateFaceOff extends FFPlayState {

	public static final int ACTION_OPENANSWER = 0x0;
	public static final int ACTION_STRIKE = 0x1;
	public static final int ACTION_CHOOSEFAMILY = 0x2;
	
	private QuestionSet questions;
	private int selectedIndex;
	private int selectedFamilyIndex = -1;
	
	protected StateFaceOff(QuestionSet questions) {
		super(FFPlayStateType.FACE_OFF);
		this.questions = questions;
	}

	@Override
	public void initState(Object data) {
		if (data instanceof Integer) {
			this.selectedIndex = (Integer) data;
		}
		else throw new InvalidDataException("StateFaceOff: Integer expected from intialization data");
	}

	@Override
	public void cleanupState() {
		// Set the selected question and selected family as the data for the next state
		data = new Object[]{questions.getQuestion(selectedIndex), selectedFamilyIndex};
	}

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_OPENANSWER:
			// Reveal an answer
			if (data[1] instanceof Integer) {
				openAnswer((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_OPENANSWER");
			break;
		case ACTION_CHOOSEFAMILY:
			// Choose the family that won the face off
			if (data[1] instanceof Integer) {
				selectFamily((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_CHOOSEFAMILY");
			break;
		case ACTION_STRIKE:
			// Incorrect answer given
			strike();
			break;

		default: 
			throw new RuntimeException("Invalid action");
		}
		return false;
	}
	
	@Override
	public boolean canAdvance() {
		// Must selected a family before advancing to the next state
		return selectedFamilyIndex >= 0;
	}
	
	/**
	 * Answer not on board
	 */
	private void strike() {
		Logger.log("Strike: answer not on board");
	}
	
	/**
	 * Reveal an answer
	 * @param index
	 */
	private void openAnswer(int index) {
		questions.getQuestion(selectedIndex).getAnswers().get(index).setReveal(true);
		Logger.log("Revealed answer: " + questions.getQuestion(selectedIndex).getAnswers().get(index));
	}
	
	/**
	 * Select the family that won the face off
	 * @param index
	 */
	private void selectFamily(int index) {
		selectedFamilyIndex = index;
	}
	
}
