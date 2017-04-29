package bwyap.familyfeud.game.play.state;

import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.QuestionSet;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;

/**
 * This class is responsible for creating and initializing the game states within the Family Feud game play.
 * Each state should only be created once.
 * @author bwyap
 *
 */
public class FFPlayStateFactory {
	
	private static FFPlayState selectQuestion;
	private static FFPlayState faceOff;
	private static FFPlayState familyPlay;
	private static FFPlayState familySteal;
	private static FFPlayState allocatePoints;
	private static FFPlayState revealAnswers;
	
	/**
	 * Get the FFPlayState object for the specified state.
	 * A new state object is created if it has not already been created.
	 * @param type
	 * @return
	 */
	public static FFPlayState getState(FFPlayStateType type, Object data) {
		switch(type) {
		case ALLOCATE_POINTS:
			if (allocatePoints == null) createAllocatePointsState();
			return allocatePoints;
		case FACE_OFF:
			if (faceOff == null) {
				if (data instanceof QuestionSet) {
					createFaceOffState((QuestionSet)data);
				}
				else throw new InvalidDataException("FFPlayStateFactory: QuestionSet expected when creating new StateFaceOff");
			}
			return faceOff;
		case FAMILY_PLAY:
			if (familyPlay == null) {
				createFamilyPlayState();
			}
			return familyPlay;
		case FAMILY_STEAL:
			if (familySteal == null) createFamilyStealState();
			return familySteal;
		case REVEAL_ANSWERS:
			if (revealAnswers == null) createRevealAnswersState();
			return revealAnswers;
		case SELECT_QUESTION:
			if (selectQuestion == null) {
				if (data instanceof QuestionSet) {
					createSelectQuestionState((QuestionSet) data);
				}
				else throw new InvalidDataException("FFPlayStateFactory: QuestionSet expected when creating new StateSelectQuestion");
			}
			return selectQuestion;
		}
		return null;
	}
	
	
	// =================
	// Create the states
	// =================
		
	private static void createSelectQuestionState(QuestionSet questions) {
		selectQuestion = new StateSelectQuestion(questions);
	}

	private static void createRevealAnswersState() {
		revealAnswers = new StateRevealAnswers();
	}

	private static void createFamilyStealState() {
		familySteal = new StateFamilySteal();
	}

	private static void createFamilyPlayState() {
		familyPlay = new StateFamilyPlay();
	}

	private static void createFaceOffState(QuestionSet questions) {
		faceOff = new StateFaceOff(questions);
	}

	private static void createAllocatePointsState() {
		allocatePoints = new StateAllocatePoints();
	}
	
}
