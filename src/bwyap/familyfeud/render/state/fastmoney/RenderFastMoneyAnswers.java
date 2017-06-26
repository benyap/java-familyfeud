package bwyap.familyfeud.render.state.fastmoney;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.RenderableString;

/**
 * This class renders the player's Fast Money answers on the screen
 * @author bwyap
 *
 */
public class RenderFastMoneyAnswers implements RenderableInterface {
	
	private FastMoney fastmoney;
	private List<RenderableString> answers;
	private List<RenderableString> scores;
	private RenderableString totalScore;
	private int score;
	
	public RenderFastMoneyAnswers(FastMoney fastmoney) {
		this.fastmoney = fastmoney;
		this.totalScore = new RenderableString("", 0, 0, "Bebas Neue", Font.PLAIN, 80, Color.WHITE, false);
		this.answers = new ArrayList<RenderableString>();
		this.scores = new ArrayList<RenderableString>();
	}
	
	@Override
	public void update(float timeElapsed) {
		score = 0;
		for(int i = 0; i < fastmoney.getNumPlayers(); i++) {
			for(int j = 0; j < FastMoney.QUESTIONS; j++) {
				if (fastmoney.getAnswer(i, j).isRevealed())
					score += fastmoney.getAnswer(i, j).getScore();
			}
		}
		totalScore.setText(score + "");
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
	}

}
