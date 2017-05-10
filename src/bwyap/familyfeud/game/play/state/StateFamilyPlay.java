package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state represents the state where a family is playing to reveal answers on the board.
 * This state should advance to the next state if the family has three strikes or has 
 * cleared the board.
 * @author bwyap
 *
 */
public class StateFamilyPlay extends FFPlayState implements StrikeInterface {

	public static final int ACTION_OPENANSWER = 0x2;
	public static final int ACTION_STRIKE = 0x3;
	public static final int ACTION_SELECTSTEALFAMILY = 0x4;
	
	private Question question;
	private int selectedFamilyIndex;
	private int strikes;
	
	protected StateFamilyPlay() {
		super(FFPlayStateType.FAMILY_PLAY);
	}
	
	@Override
	public void initState(Object data) {
		strikes = 0;
		try {
			Object[] d = (Object[]) data;
			this.question = (Question) d[0];				
			this.selectedFamilyIndex = (Integer) d[1];
		}
		catch (ClassCastException e) {
			throw new InvalidDataException("StateFamilyPlay: Question and Integer object expected from intialization data");
		}
	}

	@Override
	public void cleanupState() {
		data = new Object[]{question, selectedFamilyIndex};
		strikes = 0;
	}
	
	@Override
	public boolean canAdvance(String nextState) {
		if (nextState.equals(FFPlayStateType.ALLOCATE_POINTS.toString())) {
			boolean clearedBoard = true;
			for(Answer a : question.getAnswers()) {
				clearedBoard = clearedBoard && a.isRevealed();
			}
			return clearedBoard;
		}
		else if (nextState.equals(FFPlayStateType.FAMILY_STEAL.toString())) {
			return strikes == 3;
		}
		return false;
	}

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
		case ACTION_STRIKE:
			strike();
			return true;
		case ACTION_SELECTSTEALFAMILY:
			if (data[1] instanceof Integer) {
				selectStealFamily((Integer) data[1]);
				return true;
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_SELECTSTEALFAMILY");
		default: 
			throw new RuntimeException("Invalid action: " + action);
		}
	}
	
	/**
	 * Give the selected family a strike
	 */
	private void strike() {
		if (strikes >= FamilyFeudGame.STRIKE_LIMIT) {
			Logger.err("Strike limit reached: other family should be given the chance to steal.");
		}
		else {
			strikes++;
			Logger.log("Family [" + selectedFamilyIndex + "] now has " + strikes + " strike(s).");
		}
	}
	
	@Override
	public int getStrikes() {
		return strikes;
	}
	
	/**
	 * Reveal and answer
	 * @param index
	 */
	private void openAnswer(int index) {
		if (strikes < FamilyFeudGame.STRIKE_LIMIT) {
			if (!question.getAnswers().get(index).isRevealed()) {
				question.getAnswers().get(index).setReveal(true);
				Logger.log("Revealed answer: " + question.getAnswers().get(index));
			}
			else Logger.log("Answer already revealed!");
		}
		else Logger.err("Strike limit reached: cannot reveal more answers for family [" + selectedFamilyIndex + "]");
	}

	/**
	 * Set the family selected to attempt to steal
	 * @param index
	 */
	private void selectStealFamily(int index) {
		if (strikes == FamilyFeudGame.STRIKE_LIMIT) {
			selectedFamilyIndex = index;
			Logger.log("Family [" + selectedFamilyIndex + "] selected as potential stealers.");			
		}
		else Logger.err("Cannot assign family [" + index + "] as stealers: " + (FamilyFeudGame.STRIKE_LIMIT - strikes) + " strike(s) remaining");			
	}
	
}
