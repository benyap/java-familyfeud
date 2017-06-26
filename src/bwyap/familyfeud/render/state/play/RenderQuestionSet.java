package bwyap.familyfeud.render.state.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.Question;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.familyfeud.render.component.RenderableString;
import bwyap.familyfeud.sound.SoundManager;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A renderable interface that renders the question panel
 * @author bwyap
 *
 */
public class RenderQuestionSet implements RenderableInterface {
	
	public static final int PANEL_WIDTH = 400;
	public static final int PANEL_HEIGHT = 100;
	
	private static final int CLICK_TIME = 200;
	
	private FamilyFeudGame game;
	private Fader board;
	private RenderableString score;
	private List<RenderableString> numbers;
	private List<RenderableString> answers;
	private List<RenderableString> scores;
	private int prevRevealed;
	private float numberRevealCounter;
	private int numberRevealNext;

	public RenderQuestionSet(FamilyFeudGame game) {
		this.game = game;
		this.board = new Fader(1000, new RenderableImage(ResourceLoader.getImage("panel")), null);
		this.score = new RenderableString(null, 0, 160, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 120, Color.WHITE, true);
		this.numbers = new ArrayList<RenderableString>();
		this.answers = new ArrayList<RenderableString>();
		this.scores = new ArrayList<RenderableString>();
	}
	
	/**
	 * Reset animations on the question set board
	 */
	@Override
	public void reset() {
		board.reset();
		numbers.clear();
		answers.clear();
		scores.clear();
		prevRevealed = 0;
		numberRevealCounter = 0;
		numberRevealNext = 0;
		Question question = game.getQuestionSet().getSelectedQuestion();
		for (int i = 0; i < question.getAnswers().size(); i++) {
			if (question.getAnswers().get(i).isRevealed()) prevRevealed++;
		}
	}


	@Override
	public void update(float timeElapsed) {
		Question question = game.getQuestionSet().getSelectedQuestion();

		// load renderable strings if empty
		if (numbers.size() == 0) {
			for(int i = 0; i < question.getAnswers().size(); i++) {
				RenderableString s = new RenderableString("- " + (i + 1) + " -", 0, 0, 
						ResourceLoader.getFontName("Bebas Neue"), 
						Font.PLAIN, 90, Color.WHITE, false);
				s.setVisible(false);
				numbers.add(s);
			}
		}
		if (answers.size() == 0) {
			for(int i = 0; i < question.getAnswers().size(); i++) {
				// Create answer string
				RenderableString a = new RenderableString(question.getAnswers().get(i).getAnswerString(), 0, 0, 
						ResourceLoader.getFontName("Bebas Neue"), 
						Font.PLAIN, 90, Color.WHITE, false);
				a.setVisible(false);
				answers.add(a);

				// Create score string
				RenderableString s = new RenderableString((question.getAnswers().get(i).getValue()) + "", 
						0, 0, 
						ResourceLoader.getFontName("Bebas Neue"), 
						Font.PLAIN, 100, Color.WHITE, false);
				s.setVisible(false);
				scores.add(s);
			}
		}
		
		int num = 0;
		int revealed = 0;
		for (int i = 0; i < question.getAnswers().size(); i++) {
			if (prevRevealed > 0) {
				if (question.getAnswers().get(i).isRevealed()) {
					revealed++;
					numbers.get(i).setVisible(false);
					answers.get(i).setVisible(true);
					scores.get(i).setVisible(true);
					// Get the game score
					num += (question.getAnswers().get(i).getValue() * question.getMultiplier());
				}
				else numbers.get(i).setVisible(true);
			}
			else {
				// Incrementally display answer text if not revealed yet
				if (board.finished()) {
					if (question.getAnswers().get(i).isRevealed()) {
						revealed++;
						numbers.get(i).setVisible(false);
						answers.get(i).setVisible(true);
						scores.get(i).setVisible(true);
						// Get the game score
						num += (question.getAnswers().get(i).getValue() * question.getMultiplier());
					}
					if (i == numberRevealNext && !numbers.get(numberRevealNext).isVisible()) {
						if (numberRevealCounter < CLICK_TIME) numberRevealCounter += timeElapsed;
						else {
							numberRevealCounter = 0;
							numbers.get(numberRevealNext).setVisible(true);
							numberRevealNext++;
							SoundManager.getInstance().playClip("click");
						}
					}
				}
			}
		}
		
		if (prevRevealed != revealed) {
			prevRevealed = revealed;
		}
		
		score.setText(num + "");
		score.update(timeElapsed);
		board.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		board.render(panel, g);
		if(board.finished()) {
			score.render(panel, g);
			
			// Render numbers
			for(int i = 0; i < numbers.size(); i++) {
				RenderableString r = numbers.get(i);
				if (r.isVisible()) {
					// NOTES: uses hard-coded x values
					int x, y;
					if(UIManager.isWidescreen()) {
						x = i / 4 < 1 ? 420 - (r.getTextWidth(g, r.getFont())/2): 850 - (r.getTextWidth(g, r.getFont())/2);
						y = 325 + 113 * (i % 4);
					}
					else {
						x = i / 4 < 1 ? 280 - (r.getTextWidth(g, r.getFont())/2): 730 - (r.getTextWidth(g, r.getFont())/2);
						y = 345 + 120 * (i % 4);
					}
					r.setPosition(x, y);
					r.render(panel, g);
				}
			}
			
			for(int i = 0; i < answers.size(); i++) {
				RenderableString a = answers.get(i);
				RenderableString s = scores.get(i);
				if (a.isVisible()) {
					// NOTES: uses hard-coded x values
					if (UIManager.isWidescreen()) {
						int x = i / 4 < 1 ? 225: 655;
						int y = 325 + 113 * (i % 4);
						a.setPosition(x, y);
						s.setPosition(x + 400 - (s.getTextWidth(g, s.getFont())), y);
						if (a.getText().length() > 8) a.setSize((int)(100 * (9.0/a.getText().length())));
					}
					else {
						int x = i / 4 < 1 ? 80: 530;
						int y = 345 + 120 * (i % 4);
						a.setPosition(x, y);
						s.setPosition(x + 420 - (s.getTextWidth(g, s.getFont())), y);
						if (a.getText().length() > 7) a.setSize((int)(100 * (10.0/a.getText().length())));
					}
					a.render(panel, g);
					s.render(panel, g);
				}
			}
		}
	}
	
}
