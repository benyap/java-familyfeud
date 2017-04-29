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
public class StateFamilyPlay extends FFPlayState {

	public static final int ACTION_OPENANSWER = 0x0;
	public static final int ACTION_STRIKE = 0x1;
	
	private Question question;
	private int selectedFamilyIndex;
	private int strikes = 0;
	
	protected StateFamilyPlay() {
		super(FFPlayStateType.FAMILY_PLAY);
	}
	
	@Override
	public void initState(Object data) {
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
		if (strikes == 3) {
			// pass the question if the family is stealing
			data = question;			
		}
		else {
			// transition to clear allocate points if this family has cleared the board
			data = new Object[]{question, selectedFamilyIndex};
		}
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
			}
			else throw new InvalidDataException("Expecting a {*, Integer} when using action ACTION_OPENANSWER");
			break;
		case ACTION_STRIKE:
			strike();
			break;
		}
		return false;
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
	
	/**
	 * Reveal and answer
	 * @param index
	 */
	private void openAnswer(int index) {
		if (strikes < FamilyFeudGame.STRIKE_LIMIT) {
			question.getAnswers().get(index).setReveal(true);
			Logger.log("Revealed answer: " + question.getAnswers().get(index));
		}
		else Logger.err("Strike limit reached: cannot reveal more answers for family [" + selectedFamilyIndex + "]");
	}
	
}
