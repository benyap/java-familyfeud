package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.res.JSONQuestionSet;

public class StateLoadQuestions extends FFState {

	// ACTIONS
	public static final int ACTION_LOADQUESTIONSET = 0x0;
		
	private QuestionSet questions;
	
	protected StateLoadQuestions(QuestionSet questions) {
		super(FFStateType.LOAD_QUESTIONS);
		this.questions = questions;
	}

	@Override
	public void initState(Object data) { }

	@Override
	public void cleanupState() { }

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_LOADQUESTIONSET:
			if (data[0] instanceof JSONQuestionSet) {
				loadQuestionSet((JSONQuestionSet) data[0]);
			}
			else throw new InvalidDataException("Expecting a {JSONQuestionSet} when using action ACTION_LOADQUESTIONSET");
			break;
		}
		return false;
	}
	
	/**
	 * Load a set of questions from a JSONQuestionSet object
	 * @param familyName
	 */
	private void loadQuestionSet(JSONQuestionSet data) {
		questions.loadFromJSON(data);
	}
	
}
