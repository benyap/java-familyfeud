package bwyap.familyfeud.render;

import java.util.HashMap;
import java.util.Map;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.FFPlayStateType;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.render.state.RenderComponentContainer;
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
			if (previousStateType != state.getPlayState().getType()) {
				// Update the state if it is different and ready to update
				if (container.canTransition()) {
					switch (state.getPlayState().getType()) {
					case ALLOCATE_POINTS:
						break;
					case FACE_OFF:
						container.addComponent(renderComponents.get(RenderStateType.FACEOFF));
						container.addComponent(questionRender);
						break;
					case FAMILY_PLAY:
						break;
					case FAMILY_STEAL:
						break;
					case REVEAL_ANSWERS:
						break;
					case SELECT_QUESTION:
						break;
					default:
						break;
					}
				}
			}
			previousStateType = state.getPlayState().getType();
		}
		
		questionRender.update(timeElapsed);
		
		renderState = container;
		if (renderState != null) renderState.update(timeElapsed);
	}
	
}
