package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.FFPlayState;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.familyfeud.game.state.FFStateType;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.render.component.RenderComponentContainer;
import bwyap.familyfeud.render.state.play.RenderFaceoff;
import bwyap.familyfeud.render.state.play.RenderQuestionSet;
import bwyap.familyfeud.render.state.play.RenderSelectQuestion;

/**
 * This render machine analyzes a FamilyFeud game in the PLAY state 
 * and generates the appropriate RenderState to render the 
 * appropriate components.
 * @author bwyap
 *
 */
public class RenderPlayMachine extends AbstractRenderMachine {

	protected static final int STATE_SELECT = 0x00;
	protected static final int STATE_FACEOFF = 0x01;
	protected static final int STATE_FAMILYPLAY = 0x02;
	protected static final int STATE_FAMILYSTEAL = 0x03;
	protected static final int STATE_ALLOCATEPOINTS = 0x04;
	protected static final int STATE_REVEAL = 0x05;
	
	protected HashMap<Integer, AbstractRenderState> renderPlayStates;
	
	/**
	 * Create a new render play machine
	 * @param game
	 */
	public RenderPlayMachine(FamilyFeudGame game) {
		super(game);
	}
	
	@Override
	protected void initRenderStates() {
		renderPlayStates = new HashMap<Integer, AbstractRenderState>();
		RenderQuestionSet questionRenderer = new RenderQuestionSet(game);
		RenderComponentContainer container = null;
		
		// Create containers for each stage
		container = new RenderComponentContainer();
		container.addComponent(new RenderSelectQuestion());
		renderPlayStates.put(STATE_SELECT, container);
		
		container = new RenderComponentContainer();
		container.addComponent(new RenderFaceoff(game));
		container.addComponent(questionRenderer);
		renderPlayStates.put(STATE_FACEOFF, container);
		
		container = new RenderComponentContainer();
		container.addComponent(questionRenderer);
		renderPlayStates.put(STATE_FAMILYPLAY, container);
		
		container = new RenderComponentContainer();
		container.addComponent(questionRenderer);
		renderPlayStates.put(STATE_FAMILYSTEAL, container);

		container = new RenderComponentContainer();
		container.addComponent(questionRenderer);
		renderPlayStates.put(STATE_ALLOCATEPOINTS, container);
		
		container = new RenderComponentContainer();
		container.addComponent(questionRenderer);
		renderPlayStates.put(STATE_REVEAL, container);
	}
	
	@Override
	public void update(float timeElapsed) {
		if (game.getState().getType() == FFStateType.PLAY) {
			StatePlay state = (StatePlay) game.getState();
			FFPlayState playState = state.getPlayState();
			
			if (playState != null) {
				FFPlayStateType type = playState.getType();
				switch (type) {
				case ALLOCATE_POINTS:
					renderState = renderPlayStates.get(STATE_ALLOCATEPOINTS);
					break;
				case FACE_OFF:
					renderState = renderPlayStates.get(STATE_FACEOFF);
					break;
				case FAMILY_PLAY:
					renderState = renderPlayStates.get(STATE_FAMILYPLAY);
					break;
				case FAMILY_STEAL:
					renderState = renderPlayStates.get(STATE_FAMILYSTEAL);
					break;
				case REVEAL_ANSWERS:
					renderState = renderPlayStates.get(STATE_REVEAL);
					break;
				case SELECT_QUESTION:
					renderState = renderPlayStates.get(STATE_SELECT);
					break;
				}
			}
		}
		
		if (renderState != null) renderState.update(timeElapsed);
	}
	
}
