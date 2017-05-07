package bwyap.familyfeud.render.state.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import bwyap.familyfeud.game.Answer;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.familyfeud.render.component.RenderableString;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A renderable interface that renders the question panel
 * @author bwyap
 *
 */
public class RenderQuestionSet implements RenderableInterface {
	
	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 100;
	
	private FamilyFeudGame game;
	private Fader board;
	private RenderableString score;
	
	public RenderQuestionSet(FamilyFeudGame game) {
		this.game = game;
		this.board = new Fader(1000, new RenderableImage(ResourceLoader.getImage("panel")), null);
		this.score = new RenderableString(null, 0, 160, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 120, Color.WHITE, true);
		
	}
	
	/**
	 * Reset animations on the question set board
	 */
	public void reset() {
		board.reset();
	}

	@Override
	public void update(float timeElapsed) {
		// Get the game score
		int num = 0;
		List<Answer> answers = game.getQuestionSet().getSelectedQuestion().getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).isRevealed()) num += (answers.get(i).getValue());
		}
		
		score.setText(num + "");
		score.update(timeElapsed);
		board.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		board.render(panel, g);
		if(board.finished()) score.render(panel, g);
	}
	
}
