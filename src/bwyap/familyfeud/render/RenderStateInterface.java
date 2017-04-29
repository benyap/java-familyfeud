package bwyap.familyfeud.render;

import java.awt.Graphics;

/**
 * A state that is renderable by {@code RenderingPanel}.
 * @author bwyap
 *
 */
public interface RenderStateInterface {
	
	public void render(RenderingPanel panel, Graphics g); 
	
}
