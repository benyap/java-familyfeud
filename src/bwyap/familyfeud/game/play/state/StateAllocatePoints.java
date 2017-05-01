package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.FamilyCollection;
import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.utility.logging.Logger;

/**
 * This state is used to allocate points to the family that won the round.
 * @author bwyap
 *
 */
public class StateAllocatePoints extends FFPlayState {
	
	private int selectedFamilyIndex;
	private Question question;
	private FamilyCollection families;
	
	protected StateAllocatePoints(FamilyCollection families) {
		super(FFPlayStateType.ALLOCATE_POINTS);
		this.families = families;
	}

	@Override
	public void initState(Object data) {
		try {
			Object[] d = (Object[]) data;
			this.question = (Question)d[0];
			this.selectedFamilyIndex = (Integer)d[1];
		}
		catch (Exception e) {
			throw new InvalidDataException("StateAllocatePoints: Question and Integer object expected from intialization data");
		}
		
		// No actions needed - allocate points to the selected family right away
		int total = 0;
		for(Answer a : question.getAnswers()) {
			if (a.isRevealed()) total += a.getValue();
		}
		question.setAnswered(true);
		
		families.getFamily(selectedFamilyIndex).addPoints(total);
		
		Logger.log("Added " + total + " points to the " + 
				families.getFamily(selectedFamilyIndex).getName() + 
				" family (" + families.getFamily(selectedFamilyIndex).getPoints() + " total)");
	}

	@Override
	public void cleanupState() {
		data = question;
	}
	
}
