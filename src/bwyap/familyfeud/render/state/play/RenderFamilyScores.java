package bwyap.familyfeud.render.state.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import bwyap.familyfeud.game.Family;
import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.gui.window.GameWindow;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.RenderableString;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A renderable component that renders the name and score of each family on the screen.
 * @author bwyap
 *
 */
public class RenderFamilyScores implements RenderableInterface {
		
	private FamilyFeudGame game;
	private RenderableString title;
	private List<RenderableString> familyNames;
	private List<RenderableString> familyScores;
	
	public RenderFamilyScores(FamilyFeudGame game) {
		this.game = game;
		this.title = new RenderableString("Scoreboard", 0, 250, "Bebas Neue", Font.PLAIN, 160, Color.ORANGE, true);
		this.familyNames = new ArrayList<RenderableString>();
		this.familyScores = new ArrayList<RenderableString>();
	}
	
	@Override
	public void reset() {
		familyNames.clear();
		System.out.println("Resetting family names");
	}
	
	@Override
	public void update(float timeElapsed) {
		// Create family labels if not created
		boolean updated = false;		
		if (familyNames.size() == 0) {
			System.out.println("Creating new family names");
			familyScores.clear();
			updated = true;
			for(int i = 0; i < game.getFamilies().size(); i++) {
				Family f = game.getFamilies().get(i);
				int y = 400 + (i * 150);
				RenderableString name = new RenderableString(
						f.getName(), 
						0, y, 
						ResourceLoader.getFontName("Bebas Neue"), 100);
				name.setColor(Color.WHITE);
				
				RenderableString score = new RenderableString(
						" " + f.getPoints(), 0, y, 
						ResourceLoader.getFontName("Bebas Neue"), 100);
				score.setColor(Color.WHITE);
				
				familyNames.add(name);
				familyScores.add(score);
			}
		}
		
		// Update scores
		if (!updated) {
			for(int i = 0; i < game.getFamilies().size(); i++) {
				Family f = game.getFamilies().get(i);
				familyScores.get(i).setText(" " + f.getPoints());
			}
		}
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		title.render(panel, g);
		
		int center = GameWindow.WIDTH / 2;
		int padding = 100;
		
		// Render each family name and their scores
		for(int i = 0; i < familyNames.size(); i++) {
			RenderableString name = familyNames.get(i);
			RenderableString score = familyScores.get(i);
			
			// Set the position of each label
			name.setPosition(center - name.getTextWidth(g), name.getY());
			score.setPosition(center + padding, score.getY());
			
			name.render(panel, g);
			score.render(panel, g);
		}		
	}

}
