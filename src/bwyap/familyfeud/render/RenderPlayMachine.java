package bwyap.familyfeud.render;

import java.util.HashMap;
import java.util.Map;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.render.component.RenderComponentContainer;
import bwyap.familyfeud.render.state.RenderFaceoff;
import bwyap.familyfeud.render.state.RenderQuestionSet;

/**
 * This render machine analyzes a FamilyFeud game in the PLAY state 
 * and generates the appropriate RenderState to render the 
 * appropriate components.
 * @author bwyap
 *
 */
public class RenderPlayMachine extends AbstractRenderMachine {

	private RenderComponentContainer container;
	private Map<RenderStateType, RenderableInterface> renderComponents;
	private FFPlayStateType previousStateType;
	
	private RenderQuestionSet questionRender;
	
	/**
	 * Create a new render play machine
	 * @param game
	 */
	public RenderPlayMachine(FamilyFeudGame game) {
		super(game);
	}
	
	@Override
	protected void initRenderStates() {
		container = new RenderComponentContainer();
		questionRender = new RenderQuestionSet(game);
		renderComponents = new HashMap<RenderStateType, RenderableInterface>();
		
		renderComponents.put(RenderStateType.FACEOFF, new RenderFaceoff(game));
		//renderComponents.put(RenderStateType.FAMILYPLAY, null);
		//renderComponents.put(RenderStateType.FAMILYSTEAL, null);
		//renderComponents.put(RenderStateType.ALLOCATEPOINTS, null);
		//renderComponents.put(RenderStateType.REVEAL, null);
	}
	
	@Override
	public void update(float timeElapsed) {
		if (game.getState() instanceof StatePlay) {
			StatePlay state = (StatePlay) game.getState();
			if (state.getPlayState() != null) chooseState(state);
		}
		
		renderState = container;
		if (renderState != null) renderState.update(timeElapsed);
	}
	
	/**
	 * Choose the appropriate state to render if it has changed
	 * @param state
	 */
	private void chooseState(StatePlay state) {
		// Update the state if it is different and ready to update
		if (previousStateType != state.getPlayState().getType()) {
			if (container.canTransition()) {
				switch (state.getPlayState().getType()) {
				case ALLOCATE_POINTS:
					container.clear();
					container.addComponent(questionRender);
					break;
				case FACE_OFF:
					container.clear();
					container.addComponent(renderComponents.get(RenderStateType.FACEOFF));
					container.addComponent(questionRender);
					break;
				case FAMILY_PLAY:
					container.clear();
					container.addComponent(questionRender);
					break;
				case FAMILY_STEAL:
					container.clear();
					container.addComponent(questionRender);
					break;
				case REVEAL_ANSWERS:
					container.clear();
					container.addComponent(questionRender);
					break;
				case SELECT_QUESTION:
					container.clear();
					container.addComponent(questionRender);
					break;
				default:
					break;
				}
			}
		}
		previousStateType = state.getPlayState().getType();
	}
	
}
