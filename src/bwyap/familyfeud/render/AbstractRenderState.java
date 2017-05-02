package bwyap.familyfeud.render;

public abstract class AbstractRenderState implements RenderableInterface {

	/**
	 * Flag if the render state is ready to transition.
	 * Override this method if the render state to control
	 * when the state is ready to transition.
	 * @return
	 */
	public boolean canTransition() {
		return true;
	}
	
}
