package bwyap.familyfeud.render.state;

import java.awt.Graphics;

import bwyap.familyfeud.game.FamilyFeudGame;
import bwyap.familyfeud.render.AbstractRenderState;
import bwyap.familyfeud.render.RenderFastMoneyMachine;
import bwyap.familyfeud.render.RenderableInterface;
import bwyap.familyfeud.render.RenderingPanel;
import bwyap.familyfeud.render.component.Fader;
import bwyap.familyfeud.render.component.RenderableImage;
import bwyap.gridgame.res.ResourceLoader;

/**
 * Renders a fast money game
 * @author bwyap
 *
 */
public class RenderFastMoney extends AbstractRenderState {
	
	private RenderableImage bg;
	private Fader answerScreen;

	private RenderFastMoneyMachine machine;
	private RenderableInterface state;
	
	public RenderFastMoney(FamilyFeudGame game) {
		this.machine = new RenderFastMoneyMachine(game);

		bg = new RenderableImage(ResourceLoader.getImage("bg")); 
		answerScreen = new Fader(300, new RenderableImage(ResourceLoader.getImage("fastmoney")), null, false);
	}
	
	@Override
	public void reset() {
		answerScreen.reset();
		machine.reset();
	}
	
	@Override
	public boolean canTransition() {
		if (machine.getState() != null) {
			return machine.getState().canTransition();
		}
		return true;
	}
	
	@Override
	public void update(float timeElapsed) {
		if (!answerScreen.finished()) answerScreen.update(timeElapsed);
		machine.update(timeElapsed);
		state = machine.getState();
	}
	
	@Override
	public void render(RenderingPanel panel, Graphics g) {
		bg.render(panel, g);
		answerScreen.render(panel, g);
		if (state!= null) state.render(panel, g);
	}

}
