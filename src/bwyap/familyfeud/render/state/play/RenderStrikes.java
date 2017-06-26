package bwyap.familyfeud.render.state.play;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.game.play.state.StateFaceOff;
import bwyap.familyfeud.game.play.state.StrikeInterface;
import bwyap.familyfeud.game.state.StatePlay;
import bwyap.familyfeud.gui.window.GameWindow;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * A RenderStrikes object renders the appropriate strikes 
 * to the screen when it detects a change in the number of strikes in the 
 * current game state.
 * @author bwyap
 *
 */
public class RenderStrikes implements RenderableInterface {

	public static final int STRIKE_SIZE = 200;
	public static final int STRIKE_TIME = 1500;
	
	private FamilyFeudGame game;
	private int strikes;
	
	private RenderableImage img1;
	private RenderableImage img2;
	private RenderableImage img3;
	private Fader strike1;
	private Fader strike2;
	private Fader strike3;

	/**
	 * Create a new RenderStrikes object 
	 * @param game
	 */
	public RenderStrikes(FamilyFeudGame game) {
		this.game = game;
		this.strikes = 0;
		
		this.img1 = new RenderableImage(ResourceLoader.getImage("strike"), STRIKE_SIZE, STRIKE_SIZE);
		this.img2 = new RenderableImage(ResourceLoader.getImage("strike"), STRIKE_SIZE, STRIKE_SIZE);
		this.img3 = new RenderableImage(ResourceLoader.getImage("strike"), STRIKE_SIZE, STRIKE_SIZE);
		
		this.strike1 = new Fader(STRIKE_TIME, null, img1);
		this.strike2 = new Fader(STRIKE_TIME, null, img2);
		this.strike3 = new Fader(STRIKE_TIME, null, img3);
		
		// Strikes should initially be disabled
		this.strike1.forceFinish();
		this.strike2.forceFinish();
		this.strike3.forceFinish();
	}
	
	
	/**
	 * Reset the number of strikes
	 */
	@Override
	public void reset() {
		if (game.getState() instanceof StatePlay) {
			StatePlay play = (StatePlay) game.getState();
			if (play.getPlayState() instanceof StrikeInterface) {
				strikes = ((StrikeInterface) play.getPlayState()).getStrikes();
			}
		}
	}
	
	
	@Override
	public void update(float timeElapsed) {
		if (game.getState() instanceof StatePlay) {
			StatePlay play = (StatePlay) game.getState();
			if (play.getPlayState() instanceof StrikeInterface) {
				StrikeInterface state = (StrikeInterface) play.getPlayState();
				
				// Detect if number of strikes has changed
				if (strikes != state.getStrikes()) {
					strikes = state.getStrikes();
					
					// Check if there are strikes to be rendered
					if (strikes > 0) {
						strike1.reset();
						img1.setPosition((GameWindow.WIDTH - STRIKE_SIZE) / 2, 300);
					}
					if (!(state instanceof StateFaceOff)) {
						if (strikes > 1 && strikes < 3) {
							strike2.reset();
							img1.setPosition((GameWindow.WIDTH - STRIKE_SIZE) / 3, 300);
							img2.setPosition((GameWindow.WIDTH - STRIKE_SIZE) / 3 * 2, 300);
						}
						else if (strikes > 2) {
							strike2.reset();
							strike3.reset();
							img2.setPosition((GameWindow.WIDTH - STRIKE_SIZE)/ 4, 300);
							img3.setPosition((GameWindow.WIDTH - STRIKE_SIZE) / 4 * 3, 300);
						}
					}
				}
			}
		}
		
		if (strikes > 0 && !strike1.finished()) strike1.update(timeElapsed);
		if (strikes > 1 && !strike2.finished()) strike2.update(timeElapsed);
		if (strikes > 2 && !strike3.finished()) strike3.update(timeElapsed);
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		if (strikes > 0 && !strike1.finished()) strike1.render(panel, g);
		if (strikes > 1 && !strike2.finished()) strike2.render(panel, g);
		if (strikes > 2 && !strike3.finished()) strike3.render(panel, g);
	}
}
