package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;

/**
 * This state is used to select a question before the two families face off
 * @author bwyap
 *
 */
public class StateSelectQuestion extends FFPlayState {
	
	public static final int ACTION_SELECTQUESTION = 0x0;
	
	private QuestionSet questions;
	private int selectedIndex = -1;
	
	protected StateSelectQuestion(QuestionSet questions) {
		super(FFPlayStateType.SELECT_QUESTION);
		this.questions = questions;
	}

	@Override
	public void initState() { }

	@Override
	public void cleanupState() { 
		returnObject = selectedIndex;
	}
	
	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_SELECTQUESTION:
			if (data[2] instanceof Integer) {
				return selectQuestion((Integer) data[2]);
			}
			else throw new InvalidDataException("Expecting a {Integer, Integer, Integer} when using action ACTION_SELECTQUESTION");
		}
		return false;
	}
	
	/**
	 * Set the index for the selected question if it is within bounds.
	 * @param index
	 * @return
	 */
	private boolean selectQuestion(int index) {
		if (index < questions.size()) {
			selectedIndex = index;
			return true;
		}
		return false;
	}

}
