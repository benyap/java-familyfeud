package bwyap.familyfeud.game.state;

import bwyap.familyfeud.game.FFPlayStateMachine;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.InvalidDataException;
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
				stateMachine.getCurrentState().executeAction((Integer)data[0], data);
			}
			else throw new InvalidDataException("Expecting a {Integer, ...} when using action [" + action + "]");
			break;
		case CHANGESTATE_FACEOFF: 
			stateMachine.changeState(FFPlayStateType.FACE_OFF.toString());
			break;
		case CHANGESTATE_FAMILYPLAY: 
			stateMachine.changeState(FFPlayStateType.FAMILY_PLAY.toString());
			break;
		case CHANGESTATE_FAMILYSTEAL: 
			stateMachine.changeState(FFPlayStateType.FAMILY_STEAL.toString());
			break;
		case CHANGESTATE_ALLOCATEPOINTS:
			stateMachine.changeState(FFPlayStateType.ALLOCATE_POINTS.toString());
			break;
		case CHANGESTATE_REVEALANSWERS:
			stateMachine.changeState(FFPlayStateType.REVEAL_ANSWERS.toString());
			break;
		case CHANGESTATE_SELECTQUESTION:
			stateMachine.changeState(FFPlayStateType.SELECT_QUESTION.toString());
			break;
		}
		return false;
	}
	
}
