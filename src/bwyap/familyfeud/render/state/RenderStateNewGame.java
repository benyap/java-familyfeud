package bwyap.familyfeud.render.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderStateInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the game screen
 * @author bwyap
 *
 */
public class RenderStateNewGame implements RenderStateInterface {
	
	private FamilyFeudGame game;
	private String title = "NEW GAME";
	private int title_w = -1;
	
	public RenderStateNewGame(FamilyFeudGame game) {
		this.game = game;
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		g.drawImage(ResourceLoader.getImage("blur"), 0, 0, panel.getWidth(), panel.getHeight(), null);
		
		// Draw 'NEW GAME'
		g.setFont(new Font(ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 100));
		g.setColor(Color.WHITE);
		if (title_w == -1) {
			title_w = g.getFontMetrics().stringWidth(title);
		}
		g.drawString(title, (panel.getWidth() - title_w)/2, 150);
		
		// Draw families
		g.setFont(new Font(ResourceLoader.getFontName("Bebas Neue"), Font.PLAIN, 70));
		g.setColor(new Color(0x333333));
		int count = 0;
		int interval = g.getFontMetrics().getHeight();
		for(Family f : game.getFamilies()) {
			int w = g.getFontMetrics().stringWidth(f.toString());
			g.drawString(f.toString(), (panel.getWidth() - w)/2, 250 + interval * count++);
		}
	}

}
