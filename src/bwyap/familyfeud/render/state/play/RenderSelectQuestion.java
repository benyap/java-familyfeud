package bwyap.familyfeud.render.state.play;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;

/**
 * A state for rendering the scoreboard for the families
 * whilst the controller selects a question for the faceoff.
 * @author bwyap
 *
 */
public class RenderSelectQuestion implements RenderableInterface {
		
	private FamilyFeudGame game;
	private RenderFamilyScores scores;
	
	public RenderSelectQuestion(FamilyFeudGame game) {
		this.game = game;
		this.scores = new RenderFamilyScores(this.game);
	}
	
	@Override
	public void reset() {
		scores.reset();
	}
	
	@Override
	public void update(float timeElapsed) {
		scores.update(timeElapsed);
	}

	@Override
	public void render(RenderingPanel panel, Graphics g) {
		scores.render(panel, g);
	}

}
