package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.state.RenderStateLoading;
import bwyap.familyfeud.render.state.RenderStateMain;
import bwyap.familyfeud.render.state.RenderStateNewGame;
import bwyap.familyfeud.render.state.RenderStatePlay;

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
		renderStates.put(MAIN, new RenderStateMain());
		renderStates.put(NEWGAME, new RenderStateNewGame(game));
		renderStates.put(PLAY, new RenderStatePlay(game));
	}
	
	/**
	 * Update the render state
	 * @param timeElapse
	 */
	public void update(float timeElapse) {
		switch (game.getState().getType()) {
		case END_GAME:
			break;
		case INITIALIZE_GAME:
			renderState = renderStates.get(LOADING);
			break;
		case ADD_FAMILY:
		case LOAD_QUESTIONS:
		case NEW_GAME:
			renderState = renderStates.get(NEWGAME);
			break;
		case PLAY:
			renderState = renderStates.get(PLAY);
			break;
		case START:
			renderState = renderStates.get(MAIN);
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
