package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state is used to reveal answers left on the board
 * @author bwyap
 *
 */
public class StateRevealAnswers extends FFPlayState {

	public static final int ACTION_OPENANSWER = 0x2;
	
	private Question question;
	
	protected StateRevealAnswers() {
		super(FFPlayStateType.REVEAL_ANSWERS);
	}

	@Override
	public void initState(Object data) {
		if (data instanceof Question) {
			question = (Question) data;
		}
		else throw new InvalidDataException("StateRevealAnswers: Question object expected from intialization data");
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
				return true;
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_OPENANSWER");
		default: 
			throw new RuntimeException("Invalid action: " + action);
		}
	}
	
	/**
	 * Reveal and answer
	 * @param index
	 */
	private void openAnswer(int index) {
		if (!question.getAnswers().get(index).isRevealed()) {
			question.getAnswers().get(index).setReveal(true);
			Logger.log("Revealed answer: " + question.getAnswers().get(index));
		}
		else Logger.log("Answer already revealed!");
	}
	
}
