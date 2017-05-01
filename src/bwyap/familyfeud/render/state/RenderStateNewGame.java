package bwyap.familyfeud.render.state;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.familyfeud.render.component.RenderableString;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders the new game state:
 * each family's name will be rendered on the screen as they are added to the game.
 * @author bwyap
 *
 */
public class RenderStateNewGame implements RenderableInterface {
	
	private FamilyFeudGame game;
	
	private Fader bg;
	private RenderableString newgame;
	private List<RenderableString> families;
	
	/**
	 * Create a new game render state
	 * @param game
	 */
	public RenderStateNewGame(FamilyFeudGame game) {
		this.game = game;
		bg = new Fader(500, 
				new RenderableImage(ResourceLoader.getImage("blur")), 
				new RenderableImage(ResourceLoader.getImage("title")), false);
		
		newgame = new RenderableString("NEW GAME", 150, ResourceLoader.getFontName("Bebas Neue"), 100);
		newgame.setColor(Color.WHITE);
		
		families = new ArrayList<RenderableString>();
	}
	
	@Override
	public void update(float timeElapsed) {
		bg.update(timeElapsed);
		
		families.clear();
		final int INTERVAL = 80;
		int count = 0;
		
		try {
			for(Family f : game.getFamilies()) {
				RenderableString s = new RenderableString(f.getName(), 250 + INTERVAL * count++, ResourceLoader.getFontName("Bebas Neue"), 70);
				s.setColor(new Color(0x333333));
				families.add(s);
			}
		}
		catch (ConcurrentModificationException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
		
		// Draw 'NEW GAME'
		newgame.render(panel, g);
		
		// Draw families
		for(RenderableString s : families) {
			s.render(panel, g);
		}
	}

}
