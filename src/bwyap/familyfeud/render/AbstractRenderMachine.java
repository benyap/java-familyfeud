package bwyap.familyfeud.render;

import java.util.HashMap;

import bwyap.familyfeud.game.FamilyFeudGame;

/**
 * An abstract render machine provides a template
 * for a sub-class to generate appropriate render
 * states for a rendering panel.
 * @author bwyap
 *
 */
public abstract class AbstractRenderMachine {
		
	protected FamilyFeudGame game;
	protected RenderableInterface renderState;
	protected HashMap<RenderStateType, RenderableInterface> renderStates;

	public AbstractRenderMachine(FamilyFeudGame game) {
		this.game = game;	
		this.renderStates = new HashMap<RenderStateType, RenderableInterface>();
		initRenderStates();
	}
	
	/**
	 * Initialize initial states required for rendering
	 */
	protected abstract void initRenderStates();
	
	/**
	 * Update the machine to determine render state
	 * @param timeElapsed
	 */
	public abstract void update(float timeElapsed);

	/**
	 * Get the state to render
	 * @return
	 */
	public RenderableInterface getState() {
		return renderState;
	}
	
}
