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
	
	private FamilyFeudGame game;
	private FFPlayStateMachine stateMachine;
	
	protected StatePlay(FamilyFeudGame game) {
		super(FFStateType.PLAY);
		this.game = game;
	}

	@Override
	public void initState(Object data) {
		stateMachine = new FFPlayStateMachine(game.getQuestionSet());
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
		}
		return false;
	}
	
}
