package bwyap.familyfeud.render.state.fastmoney;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.gui.UIManager;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.RenderableString;
import bwyap.gridgame.res.ResourceLoader;

/**
 * This class renders the player's Fast Money answers on the screen
 * @author bwyap
 *
 */
public class RenderFastMoneyAnswers implements RenderableInterface {
	
	private static final int COLUMNS = 2;
	
	private FastMoney fastmoney;
	private List<RenderableString> answers1;
	private List<RenderableString> scores1;
	private List<RenderableString> answers2;
	private List<RenderableString> scores2;
	private RenderableString totalScore;
	private int score;
	
	public RenderFastMoneyAnswers(FastMoney fastmoney) {
		this.fastmoney = fastmoney;
		this.totalScore = new RenderableString("", 0, 0, "Bebas Neue", Font.PLAIN, 80, Color.WHITE, false);
		this.answers1 = new ArrayList<RenderableString>();
		this.scores1 = new ArrayList<RenderableString>();
		this.answers2 = new ArrayList<RenderableString>();
		this.scores2 = new ArrayList<RenderableString>();
	}
	
	@Override
	public void update(float timeElapsed) {
		// Set the score
		score = 0;
		for(int i = 0; i < COLUMNS; i++) {
			for(int j = 0; j < FastMoney.QUESTIONS; j++) {
				if (fastmoney.getAnswer(i, j).isRevealedScore() && fastmoney.getAnswer(i, j).getScore() >= 0) {
					score += fastmoney.getAnswer(i, j).getScore();
				}
			}
		}
		totalScore.setText(score + "");
		
		// Initialise answers and scores
		if (answers1.size() == 0) {
			for(int i = 0; i < FastMoney.QUESTIONS; i++) {
				RenderableString s;
				s = new RenderableString("", 0, 0, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 70, Color.WHITE, false);
				s.setVisible(true);
				answers1.add(s);
				s = new RenderableString("", 0, 0, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 70, Color.WHITE, false);
				s.setVisible(true);
				answers2.add(s);
				s = new RenderableString("", 0, 0, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 70, Color.WHITE, false);
				s.setVisible(true);
				scores1.add(s);
				s = new RenderableString("", 0, 0, ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 70, Color.WHITE, false);
				s.setVisible(true);
				scores2.add(s);
			}
		}
		
		// Set the answers and numbers text for both players
		for(int i = 0; i < FastMoney.QUESTIONS; i++) {
			answers1.get(i).setText(fastmoney.getAnswer(0, i).getAnswer());
			answers1.get(i).setVisible(fastmoney.getAnswer(0, i).isRevealedAnswer());
			
			scores1.get(i).setText(fastmoney.getAnswer(0, i).getScore() + "");
			scores1.get(i).setVisible(fastmoney.getAnswer(0, i).isRevealedScore());
			
			answers2.get(i).setText(fastmoney.getAnswer(1, i).getAnswer());
			answers2.get(i).setVisible(fastmoney.getAnswer(1, i).isRevealedAnswer());
			
			scores2.get(i).setText(fastmoney.getAnswer(1, i).getScore() + "");
			scores2.get(i).setVisible(fastmoney.getAnswer(1, i).isRevealedScore());
		}
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		// Render the score
		int x, y, offset;
		offset = score == 0 ? 0 : ((int) Math.log10(score));
		if (bwyap.familyfeud.gui.UIManager.isWidescreen()) {
			y = (int)(1055 / panel.getRatio());
			x = (int)(1820 / panel.getRatio()) - (int)(offset * 56/panel.getRatio()); 
		}
		else {
			y = (int)(830 / panel.getRatio());
			x = (int)(1215 / panel.getRatio()) - (int)(offset * 40/panel.getRatio()); 
		}
		totalScore.setPosition(x, y);
		totalScore.render(panel, g);
		
		// Render player 1
		renderAnswers(0, panel, g);
		
		// Render player 2
		renderAnswers(1, panel, g);
	}
	
	/**
	 * Render the answers for a player
	 * @param player
	 * @param panel
	 * @param g
	 */
	private void renderAnswers(int player, RenderingPanel panel, Graphics g) {
		if (fastmoney.showAnswers(player)) {
			for(int i = 0; i < FastMoney.QUESTIONS; i++) {
				RenderableString a = player == 0 ? answers1.get(i) : answers2.get(i);
				RenderableString s = player == 0 ? scores1.get(i) : scores2.get(i);
				a.setSize(70);
				s.setSize(70);
				if (a.isVisible()) {
					// NOTES: uses hard-coded x values
					if (UIManager.isWidescreen()) {
						int x = player == 0 ? 225: 655;
						int y = 160 + 86 * (i % 5);
						a.setPosition(x, y);
						s.setPosition(x + 400 - (s.getTextWidth(g, s.getFont())), y);
						if (a.getText().length() > 11) a.setSize((int)(80 * (10.0/a.getText().length())));
					}
					else {
						int x = player == 0 ? 80: 525;
						int y = 175 + 89 * (i % 5);
						a.setPosition(x, y);
						s.setPosition(x + 420 - (s.getTextWidth(g, s.getFont())), y);
						if (a.getText().length() > 10) a.setSize((int)(100 * (8.0/a.getText().length())));
					}
					a.render(panel, g);
					s.render(panel, g);
				}
			}
		}
	}

}
