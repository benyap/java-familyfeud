package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;

/**
 * This state handles when the two families are facing off 
 * to see which one gets to choose to play
 * @author bwyap
 *
 */
public class StateFaceOff extends FFPlayState {

	public static final int ACTION_OPENANSWER = 0x0;
	
	private QuestionSet questions;
	private int selectedIndex;
	
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
	public void cleanupState() { }

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
		}
		return false;
	}
	
	/**
	 * Reveal and answer
	 * @param index
	 */
	private void openAnswer(int index) {
		questions.getQuestion(selectedIndex).getAnswers().get(index).setReveal(true);
	}

}
