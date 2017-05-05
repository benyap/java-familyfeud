package bwyap.familyfeud.render.state.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;

/**
 * A renderable interface that renders the question panel
 * @author bwyap
 *
 */
public class RenderQuestionSet implements RenderableInterface {
	
	private FamilyFeudGame game;
	
	public RenderQuestionSet(FamilyFeudGame game) {
		this.game = game;
	}

	@Override
	public void update(float timeElapsed) {
		
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		// TESTING
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.BOLD, 40));
		g.drawString(game.getQuestionSet().getSelectedQuestion().getQuestionString(), 10, 100);
	}
	
}
