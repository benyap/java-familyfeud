package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;

/**
 * Render the faceoff phase
 * @author bwyap
 *
 */
public class RenderFaceoff implements RenderableInterface {

	private FamilyFeudGame game;
	
	public RenderFaceoff(FamilyFeudGame game) {
		this.game = game;
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		
	}

}
