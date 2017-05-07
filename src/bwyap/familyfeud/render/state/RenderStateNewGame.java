package bwyap.familyfeud.render.state;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.AbstractRenderState;
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
public class RenderStateNewGame extends AbstractRenderState {
	
	private FamilyFeudGame game;
	
	private Fader bg;
	private Fader load;
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
		
		load = new Fader(300,
				new RenderableImage(ResourceLoader.getImage("blur")),
				null, false);
		
		newgame = new RenderableString("NEW GAME", 150, ResourceLoader.getFontName("Bebas Neue"), 100);
		newgame.setColor(Color.WHITE);
		
		families = new ArrayList<RenderableString>();
	}
	
	@Override
	public void update(float timeElapsed) {
		bg.update(timeElapsed);
		
		final int INTERVAL = 80;
		int count = 0;
		families.clear();
		List<Family> f = game.getFamilies();
		for(int i = 0; i < f.size(); i++) {
			RenderableString s = new RenderableString(f.get(i).getName(), 250 + INTERVAL * count++, ResourceLoader.getFontName("Bebas Neue"), 70);
			s.setColor(new Color(0x333333));
			families.add(s);
		}
		
		switch (game.getState().getType()) {
		case ADD_FAMILY:
		case LOAD_QUESTIONS:
		case NEW_GAME:
			break;
		default: 
			load.update(timeElapsed); 
			break;
		}
	}
	
	@Override
	public boolean canTransition() {
		return load.finished();
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
		
		// Draw 'NEW GAME'
		newgame.render(panel, g);
		
		// Draw families
		for(int i = 0; i < families.size(); i++) {
			if (families.get(i) != null)
				families.get(i).render(panel, g);
		}
		
		load.render(panel, g);
	}

}
