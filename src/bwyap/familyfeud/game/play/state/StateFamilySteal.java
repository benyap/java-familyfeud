package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state represents the opportunity an opposing team has to 
 * steal the points from the board if they can guess an answer on 
 * the board.
 * @author bwyap
 *
 */
public class StateFamilySteal extends FFPlayState {

	public static final int ACTION_OPENANSWER = 0x0;
	public static final int ACTION_STRIKE = 0x1;
	public static final int ACTION_SELECTSTEALFAMILY = 0x2;
	public static final int ACTION_SELECTWINFAMILY = 0x3;
	
	private Question question;
	private int selectedWinFamilyIndex = -1;
	private int selectedStealFamilyIndex = -1;
	private int strikes = 0;

	protected StateFamilySteal() {
		super(FFPlayStateType.FAMILY_STEAL);
	}

	@Override
	public void initState(Object data) {
		if (data instanceof Question) {
			this.question = (Question) data;
		}
		else throw new InvalidDataException("StateFamilySteal: Question object expected from intialization data");
	}

	@Override
	public void cleanupState() {
		// Pass the family selected to receive the points
		data = new Object[]{question, selectedWinFamilyIndex};
	}

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_SELECTSTEALFAMILY:
			// Reveal an answer
			if (data[1] instanceof Integer) {
				selectStealFamily((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_SELECTSTEALFAMILY");
			break;
		case ACTION_OPENANSWER:
			// Reveal an answer
			if (data[1] instanceof Integer) {
				openAnswer((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_OPENANSWER");
			break;
		case ACTION_STRIKE:
			strike();
			break;
		case ACTION_SELECTWINFAMILY:
			// Reveal an answer
			if (data[1] instanceof Integer) {
				selectWinFamily((Integer) data[1]);
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_SELECTWINFAMILY");
			break;
		}
		return false;
	}
	
	/**
	 * Give the selected family a strike
	 */
	private void strike() {
		if (selectedStealFamilyIndex < 0) {
			Logger.err("No family selected as potential stealers");
		}
		else if (strikes > 0) {
			Logger.err("Only one strike permitted in a steal phase. Please selected a winning team.");
		}
		else {
			strikes++;
			Logger.log("Family [" + selectedStealFamilyIndex + "] now has " + strikes + " strike(s).");			
		}
	}
	
	/**
	 * Reveal and answer
	 * @param index
	 */
	private void openAnswer(int index) {
		if (selectedStealFamilyIndex > -1) {			
			question.getAnswers().get(index).setReveal(true);
		}
		else Logger.err("No family selected as potential stealers");
	}
	
	/**
	 * Set the family selected to attempt to steal
	 * @param index
	 */
	private void selectStealFamily(int index) {
		selectedStealFamilyIndex = index;
		Logger.log("Family [" + selectedStealFamilyIndex + "] selected as potential stealers.");
	}
	
	/**
	 * Set the family selected to win the points
	 * @param index
	 */
	private void selectWinFamily(int index) {
		selectedWinFamilyIndex = index;
		Logger.log("Family [" + selectedWinFamilyIndex + "] selected to receive points.");
	}
	
}
