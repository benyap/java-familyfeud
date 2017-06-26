package bwyap.familyfeud.render.state.fastmoney;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.game.fastmoney.state.FastMoney;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.RenderableString;

/**
 * This class renders the count down timer for Fast Money
 * @author bwyap
 *
 */
public class RenderFastMoneyTimer implements RenderableInterface {
	
	private FastMoney fastmoney;
	
	private RenderableString timer;
	
	public RenderFastMoneyTimer(FastMoney fastmoney) {
		this.fastmoney = fastmoney;
		this.timer = new RenderableString("", 0, 660, "Bebas Neue", Font.PLAIN, 80, Color.WHITE, true);
	}

	@Override
	public void update(float timeElapsed) {
		if (fastmoney.isTimerRunning()) {
			fastmoney.updateTimer(timeElapsed);			
		}
		timer.setText((int)fastmoney.getTimer() + "");
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		if (bwyap.familyfeud.gui.UIManager.isWidescreen()) {
			timer.setPosition(0, (int)(1170 / panel.getRatio()));
		}
		else {
			timer.setPosition(0, (int)(920 / panel.getRatio()));
		}
		timer.render(panel, g);
	}
	
}
