package bwyap.familyfeud.render;

import bwyap.familyfeud.game.FamilyFeudGame;

/**
 * This render machine analyzes a FamilyFeud game in the PLAY state 
 * and generates the appropriate RenderState to render the 
 * appropriat components.
 * @author bwyap
 *
 */
public class RenderPlayMachine extends AbstractRenderMachine {

	public RenderPlayMachine(FamilyFeudGame game) {
		super(game);
	}
	
	@Override
	protected void initRenderStates() {
		// TODO
	}
	
	@Override
	public void update(float timeElapsed) {
		
		//TODO
		
		if (renderState != null) renderState.update(timeElapsed);
	}
	
}
