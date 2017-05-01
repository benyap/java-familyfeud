package bwyap.familyfeud.render.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the game screen
 * @author bwyap
 *
 */
public class RenderStateNewGame implements RenderableInterface {
	
	private FamilyFeudGame game;
	
	private String title = "NEW GAME";
	private int title_w = -1;
	private Fader bg;
	
	public RenderStateNewGame(FamilyFeudGame game) {
		this.game = game;
		RenderableImage over = new RenderableImage(ResourceLoader.getImage("blur"));
		RenderableImage base = new RenderableImage(ResourceLoader.getImage("title"));
		bg = new Fader(1000, over, base, false);
	}
	
	@Override
	public void update(float timeElapsed) {
		bg.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
		
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
