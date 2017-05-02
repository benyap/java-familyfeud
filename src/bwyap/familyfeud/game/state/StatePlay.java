package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FFPlayStateMachine;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.InvalidDataException;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;

/**
 * This state is used to control the game play
 * @author bwyap
 *
 */
public class StatePlay extends FFState {
	
	public static final int ACTION_EXECUTEPLAYACTION = 0x00;
	public static final int CHANGESTATE_FACEOFF = 0x10;
	public static final int CHANGESTATE_FAMILYPLAY = 0x11;
	public static final int CHANGESTATE_FAMILYSTEAL = 0x12;
	public static final int CHANGESTATE_ALLOCATEPOINTS = 0x13;
	public static final int CHANGESTATE_REVEALANSWERS = 0x14;
	public static final int CHANGESTATE_SELECTQUESTION = 0x15;
	
	private FamilyFeudGame game;
	private FFPlayStateMachine stateMachine;
	
	protected StatePlay(FamilyFeudGame game) {
		super(FFStateType.PLAY);
		this.game = game;
	}

	@Override
	public void initState(Object data) {
		stateMachine = new FFPlayStateMachine(game.getQuestionSet(), game.getFamilyCollection());
		stateMachine.init();
	}
	
	@Override
	public void cleanupState() { }

	@Override
	public boolean executeAction(int action, Object[] data) {
		switch(action) {
		case ACTION_EXECUTEPLAYACTION:
			if (data[0] instanceof Integer) {
				return stateMachine.getCurrentState().executeAction((Integer)data[0], data);
			}
			else throw new InvalidDataException("Expecting a {Integer, ...} when using action [" + action + "]");
		case CHANGESTATE_FACEOFF: 
			return stateMachine.changeState(FFPlayStateType.FACE_OFF.toString());
		case CHANGESTATE_FAMILYPLAY: 
			return stateMachine.changeState(FFPlayStateType.FAMILY_PLAY.toString());
		case CHANGESTATE_FAMILYSTEAL: 
			return stateMachine.changeState(FFPlayStateType.FAMILY_STEAL.toString());
		case CHANGESTATE_ALLOCATEPOINTS:
			return stateMachine.changeState(FFPlayStateType.ALLOCATE_POINTS.toString());
		case CHANGESTATE_REVEALANSWERS:
			return stateMachine.changeState(FFPlayStateType.REVEAL_ANSWERS.toString());
		case CHANGESTATE_SELECTQUESTION:
			return stateMachine.changeState(FFPlayStateType.SELECT_QUESTION.toString());
		}
		return false;
	}
	
	/**
	 * Get the current play state
	 * @return
	 */
	public FFPlayState getPlayState() {
		if (stateMachine!= null) return stateMachine.getCurrentState();
		return null;
	}
	
}
