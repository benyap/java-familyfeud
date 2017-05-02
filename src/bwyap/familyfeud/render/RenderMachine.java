package bwyap.familyfeud.render;

import static bwyap.familyfeud.render.RenderStateType.LOADING;
import static bwyap.familyfeud.render.RenderStateType.MAIN;
import static bwyap.familyfeud.render.RenderStateType.NEWGAME;
import static bwyap.familyfeud.render.RenderStateType.PLAY;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.state.RenderStateLoading;
import bwyap.familyfeud.render.state.RenderStateTitle;
import bwyap.familyfeud.render.state.RenderStateNewGame;
import bwyap.familyfeud.render.state.RenderStatePlay;

/**
 * The RenderMachine analyzes a FamilyFeud game and generates 
 * the appropriate RenderState for the render engine.
 * @author bwyap
 *
 */
public class RenderMachine extends AbstractRenderMachine {
	
	/**
	 * Create a render machine that generates Family Feud render states
	 * @param game
	 */
	public RenderMachine(FamilyFeudGame game) {
		super(game);
	}
	
	@Override
	protected void initRenderStates() {		
		renderStates.put(LOADING, new RenderStateLoading());
		renderStates.put(MAIN, new RenderStateTitle());
		renderStates.put(NEWGAME, new RenderStateNewGame(game));
		renderStates.put(PLAY, new RenderStatePlay(game));
	}
	
	@Override
	public void update(float timeElapsed) {
		if (renderState == null) {
			switchState();
		}
		else if (renderState.canTransition()) {
			switchState();
		}

		if (renderState != null) renderState.update(timeElapsed);
	}
	
	/**
	 * Assign the appropriate state according to the current game state
	 */
	private void switchState() {
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
	
}
