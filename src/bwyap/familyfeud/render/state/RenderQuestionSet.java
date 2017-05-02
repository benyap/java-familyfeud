package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

public class RenderQuestionSet implements RenderableInterface {
	
	private FamilyFeudGame game;
	private RenderableImage render;
	private RenderableImage answer6;
	private RenderableImage answer8;
	
	public RenderQuestionSet(FamilyFeudGame game) {
		this.game = game;
		answer6 = new RenderableImage(ResourceLoader.getImage("answer6"));
		answer8 = new RenderableImage(ResourceLoader.getImage("answer8"));
	}

	@Override
	public void update(float timeElapsed) {
		if (game.getQuestionSet().getSelectedQuestion() != null) {
			if (game.getQuestionSet().getSelectedQuestion().getAnswers().size() > 6) {
				render = answer8;
			}
			else {
				render = answer6;
			}
		}
		if (render != null) render.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		if (render != null) render.render(panel, g);
	}
	
}
