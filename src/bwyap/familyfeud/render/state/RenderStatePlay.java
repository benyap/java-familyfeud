package bwyap.familyfeud.render.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the game screen
 * @author bwyap
 *
 */
public class RenderStatePlay implements RenderableInterface {
	
	private FamilyFeudGame game;
	private String title = "PLAY";
	private int title_w = -1;
	
	public RenderStatePlay(FamilyFeudGame game) {
		this.game = game;
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(ResourceLoader.getImage("bg"), 0, 0, panel.getWidth(), panel.getHeight(), null);
		
		// Draw 'NEW GAME'
		g.setFont(new Font(ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 100));
		g.setColor(Color.WHITE);
		if (title_w == -1) {
			title_w = g.getFontMetrics().stringWidth(title);
		}
		g.drawString(title, (panel.getWidth() - title_w)/2, 150);
		
	}

}
