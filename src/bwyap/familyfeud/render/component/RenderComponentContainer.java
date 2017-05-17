package bwyap.familyfeud.render.component;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;

/**
 * This class holds a list of renderable components to render.
 * @author bwyap
 *
 */
public class RenderComponentContainer extends AbstractRenderState {

	private List<RenderableInterface> components;
	
	/**
	 * Create a new render component container.
	 */
	public RenderComponentContainer() {
		components = new LinkedList<RenderableInterface>();
	}
	
	/**
	 * Clear the components in the container
	 */
	public void clear() {
		components.clear();
	}
	
	/**
	 * Add a render component to be rendered
	 * @param state
	 */
	public void addComponent(RenderableInterface component) {
		components.add(component);
	}
	
	/**
	 * Insert a render component into the container at the specified index
	 * @param index
	 * @param state
	 */
	public void insertState(int index, RenderableInterface component) {
		components.add(index, component);
	}
	
	/**
	 * Remove a component from the rendering container by index
	 * @param index
	 */
	public void removeState(int index) {
		components.remove(index);
	}
	
	/**
	 * Remove a component from the rendering container
	 * @param state
	 */
	public void removeState(RenderableInterface component) {
		components.remove(component);
	}
	
	@Override
	public void reset() {
		// Reset each state
		for(RenderableInterface component : components) {
			component.reset();
		}
	}
	
	@Override
	public void update(float timeElapsed) {
		// Update each state
		for(RenderableInterface component : components) {
			component.update(timeElapsed);
		}
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		// Render each state in order
		for(RenderableInterface component : components) {
			component.render(panel, g);
		}
	}
	
}
