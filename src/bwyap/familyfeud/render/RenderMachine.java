package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.state.RenderStateLoading;
import static bwyap.familyfeud.render.RenderStateType.*;

/**
 * The RenderMachine analyzes a FamilyFeud game and generates 
 * the appropriate RenderState for the render engine.
 * @author bwyap
 *
 */
public class RenderMachine {
	
	private FamilyFeudGame game;
	private RenderStateInterface renderState;
	private HashMap<RenderStateType, RenderStateInterface> renderStates;
	
	public RenderMachine(FamilyFeudGame game) {
		this.game = game;		
		initRenderStates();
	}
	
	private void initRenderStates() {
		renderStates = new HashMap<RenderStateType, RenderStateInterface>();
		
		renderStates.put(LOADING, new RenderStateLoading());

	}
	
	/**
	 * Update the render state
	 * @param timeElapse
	 */
	public void update(float timeElapse) {
		switch (game.getState().getType()) {
		case ADD_FAMILY:
			break;
		case END_GAME:
			break;
		case INITIALIZE_GAME:
			break;
		case LOAD_QUESTIONS:
			break;
		case NEW_GAME:
			break;
		case PLAY:
			break;
		case START:
			renderState = renderStates.get(LOADING);
			break;
		}
	}
	
	/**
	 * Get the state to render
	 * @return
	 */
	public RenderStateInterface getState() {
		return renderState;
	}
	
}
