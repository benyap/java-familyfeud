package bwyap.familyfeud.render;

import java.awt.Graphics;

/**
 * An object that is renderable by {@code RenderingPanel} using the Graphics API.
 * @author bwyap
 *
 */
public interface RenderableInterface {
	
	/**
	 * Update the renderable component
	 * @param timeElapsed
	 */
	default void update(float timeElapsed) { }
	
	/**
	 * Render the component using the Graphics API
	 * @param panel
	 * @param g
	 */
	public void render(RenderingPanel panel, Graphics g); 
	
}
