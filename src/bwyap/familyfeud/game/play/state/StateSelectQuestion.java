package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state is used to select a question before the two families face off
 * @author bwyap
 *
 */
public class StateSelectQuestion extends FFPlayState {
	
	public static final int ACTION_SELECTQUESTION = 0x0;
	
	private QuestionSet questions;
	private int selectedIndex;
	
	protected StateSelectQuestion(QuestionSet questions) {
		super(FFPlayStateType.SELECT_QUESTION);
		this.questions = questions;
	}

	@Override
	public void initState(Object data) {
		selectedIndex = -1;
	}

	@Override
	public void cleanupState() {
		data = selectedIndex;
	}
	
	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_SELECTQUESTION:
			if (data[1] instanceof Integer) {
				return selectQuestion((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_SELECTQUESTION");
		default: 
			throw new RuntimeException("Invalid action: " + action);
		}
	}
	
	/**
	 * Set the index for the selected question if it is within bounds.
	 * @param index
	 * @return
	 */
	private boolean selectQuestion(int index) {
		if (index < questions.size()) {
			selectedIndex = index;
			questions.setSelectedQuestion(selectedIndex);
			Logger.log("Question [" + selectedIndex +  "] selected.");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canAdvance(String nextState) {
		return selectedIndex > -1;
	}

}
